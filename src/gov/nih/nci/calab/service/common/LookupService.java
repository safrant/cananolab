package gov.nih.nci.calab.service.common;

import gov.nih.nci.calab.db.DataAccessProxy;
import gov.nih.nci.calab.db.IDataAccess;
import gov.nih.nci.calab.domain.Aliquot;
import gov.nih.nci.calab.domain.MeasureUnit;
import gov.nih.nci.calab.domain.StorageElement;
import gov.nih.nci.calab.domain.User;
import gov.nih.nci.calab.domain.SampleContainer;
import gov.nih.nci.calab.dto.common.UserBean;
import gov.nih.nci.calab.dto.inventory.AliquotBean;
import gov.nih.nci.calab.dto.inventory.ContainerBean;
import gov.nih.nci.calab.dto.inventory.ContainerInfoBean;
import gov.nih.nci.calab.dto.inventory.SampleBean;
import gov.nih.nci.calab.dto.workflow.AssayBean;
import gov.nih.nci.calab.service.util.CalabConstants;
import gov.nih.nci.calab.service.util.CalabComparators;
import gov.nih.nci.calab.service.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;

/**
 * The service to return prepopulated data that are shared across different
 * views.
 * 
 * @author zengje
 * 
 */
/* CVS $Id: LookupService.java,v 1.30 2006-06-30 20:54:30 pansu Exp $ */

public class LookupService {
	private static Logger logger = Logger.getLogger(LookupService.class);

	/**
	 * Retrieving all unmasked aliquots for use in views create run, create
	 * assay run, use aliquot and create aliquot.
	 * 
	 * @return a Map between sample name and its associated unmasked aliquots
	 * @throws Exception
	 */
	public Map<String, SortedSet<AliquotBean>> getUnmaskedSampleAliquots()
			throws Exception {
		SortedSet<AliquotBean> aliquots = null;
		IDataAccess ida = (new DataAccessProxy())
				.getInstance(IDataAccess.HIBERNATE);
		Map<String, SortedSet<AliquotBean>> sampleAliquots = new HashMap<String, SortedSet<AliquotBean>>();
		try {
			ida.open();
			String hqlString = "select aliquot.id, aliquot.name, aliquot.sample.name from Aliquot aliquot where aliquot.dataStatus is null order by aliquot.name";
			List results = ida.search(hqlString);
			for (Object obj : results) {
				Object[] info = (Object[]) obj;
				AliquotBean aliquot = new AliquotBean(StringUtils
						.convertToString(info[0]), StringUtils
						.convertToString(info[1]), CalabConstants.ACTIVE_STATUS);
				String sampleName = (String) info[2];
				if (sampleAliquots.get(sampleName) != null) {
					aliquots = (SortedSet<AliquotBean>) sampleAliquots
							.get(sampleName);
				} else {
					aliquots = new TreeSet<AliquotBean>(
							new CalabComparators.AliquotBeanComparator());
					sampleAliquots.put(sampleName, aliquots);
				}
				aliquots.add(aliquot);
			}
		} catch (Exception e) {
			logger.error("Error in retrieving all aliquot Ids and names", e);
			throw new RuntimeException(
					"Error in retrieving all aliquot Ids and names");
		} finally {
			ida.close();
		}
		return sampleAliquots;
	}

	public Map<String, SortedSet<ContainerBean>> getSampleContainers()
			throws Exception {
		SortedSet<ContainerBean> containers = null;
		IDataAccess ida = (new DataAccessProxy())
				.getInstance(IDataAccess.HIBERNATE);
		Map<String, SortedSet<ContainerBean>> sampleContainers = new HashMap<String, SortedSet<ContainerBean>>();
		try {
			ida.open();
			String hqlString = "select container, container.sample.name from SampleContainer container";
			List results = ida.search(hqlString);
			for (Object obj : results) {
				Object[] info = (Object[]) obj;
				if (!(info[0] instanceof Aliquot)) {
					ContainerBean container = new ContainerBean(
							(SampleContainer) info[0]);

					String sampleName = (String) info[1];
					if (sampleContainers.get(sampleName) != null) {
						containers = (SortedSet<ContainerBean>) sampleContainers
								.get(sampleName);
					} else {
						containers = new TreeSet<ContainerBean>(
								new CalabComparators.ContainerBeanComparator());
						sampleContainers.put(sampleName, containers);
					}
					containers.add(container);
				}
			}
		} catch (Exception e) {
			logger.error("Error in retrieving all containers", e);
			throw new RuntimeException("Error in retrieving all containers");
		} finally {
			ida.close();
		}
		return sampleContainers;
	}

	/**
	 * Retrieving all sample types.
	 * 
	 * @return a list of all sample types
	 */
	public List<String> getAllSampleTypes() throws Exception {
		// Detail here
		// Retrieve data from Sample_Type table
		List<String> sampleTypes = new ArrayList<String>();
		sampleTypes.add(CalabConstants.OTHER);
		IDataAccess ida = (new DataAccessProxy())
				.getInstance(IDataAccess.HIBERNATE);
		try {
			ida.open();
			String hqlString = "select sampleType.name from SampleType sampleType order by sampleType.name";
			List results = ida.search(hqlString);
			for (Object obj : results) {
				sampleTypes.add((String) obj);
			}
		} catch (Exception e) {
			logger.error("Error in retrieving all sample types", e);
			throw new RuntimeException("Error in retrieving all sample types");
		} finally {
			ida.close();
		}

		return sampleTypes;
	}

	/**
	 * 
	 * @return the default sample container information in a form of
	 *         ContainerInfoBean
	 */
	public ContainerInfoBean getSampleContainerInfo() throws Exception {

		List<MeasureUnit> units = getAllMeasureUnits();
		List<StorageElement> storageElements = getAllRoomAndFreezers();
		List<String> quantityUnits = new ArrayList<String>();
		List<String> concentrationUnits = new ArrayList<String>();
		List<String> volumeUnits = new ArrayList<String>();
		List<String> rooms = new ArrayList<String>();
		List<String> freezers = new ArrayList<String>();

		for (MeasureUnit unit : units) {
			if (unit.getType().equalsIgnoreCase("Quantity")) {
				quantityUnits.add(unit.getName());
			} else if (unit.getType().equalsIgnoreCase("Volume")) {
				volumeUnits.add(unit.getName());
			} else if (unit.getType().equalsIgnoreCase("Concentration")) {
				concentrationUnits.add(unit.getName());
			}
		}

		for (StorageElement storageElement : storageElements) {
			if (storageElement.getType().equalsIgnoreCase("Room")) {
				rooms.add((storageElement.getLocation()));
			} else if (storageElement.getType().equalsIgnoreCase("Freezer")) {
				freezers.add((storageElement.getLocation()));
			}
		}

		// set labs and racks to null for now
		ContainerInfoBean containerInfo = new ContainerInfoBean(quantityUnits,
				concentrationUnits, volumeUnits, null, rooms, freezers);

		return containerInfo;
	}

	public List<String> getAllSampleContainerTypes() throws Exception {
		SortedSet<String> containerTypes = new TreeSet<String>();
		IDataAccess ida = (new DataAccessProxy())
				.getInstance(IDataAccess.HIBERNATE);
		try {
			ida.open();
			String hqlString = "select distinct container.containerType from SampleContainer container order by container.containerType";
			List results = ida.search(hqlString);
			for (Object obj : results) {
				containerTypes.add((String) obj);
			}
		} catch (Exception e) {
			logger.error("Error in retrieving all sample container types", e);
			throw new RuntimeException(
					"Error in retrieving all sample container types.");
		} finally {
			ida.close();
		}
		containerTypes.addAll(Arrays
				.asList(CalabConstants.DEFAULT_CONTAINER_TYPES));
		List<String> containerTypeList = new ArrayList<String>(containerTypes);

		return containerTypeList;
	}

	public List<String> getAllAliquotContainerTypes() throws Exception {
		SortedSet<String> containerTypes = new TreeSet<String>();
		IDataAccess ida = (new DataAccessProxy())
				.getInstance(IDataAccess.HIBERNATE);
		try {
			ida.open();
			String hqlString = "select distinct aliquot.containerType from Aliquot aliquot order by aliquot.containerType";
			List results = ida.search(hqlString);
			for (Object obj : results) {
				containerTypes.add((String) obj);
			}
		} catch (Exception e) {
			logger.error("Error in retrieving all aliquot container types", e);
			throw new RuntimeException(
					"Error in retrieving all aliquot container types.");
		} finally {
			ida.close();
		}
		containerTypes.addAll(Arrays
				.asList(CalabConstants.DEFAULT_CONTAINER_TYPES));
		List<String> containerTypeList = new ArrayList<String>(containerTypes);
		return containerTypeList;
	}

	private List<MeasureUnit> getAllMeasureUnits() throws Exception {
		List<MeasureUnit> units = new ArrayList<MeasureUnit>();
		IDataAccess ida = (new DataAccessProxy())
				.getInstance(IDataAccess.HIBERNATE);
		try {
			ida.open();
			String hqlString = "from MeasureUnit";
			List results = ida.search(hqlString);
			for (Object obj : results) {
				units.add((MeasureUnit) obj);
			}
		} catch (Exception e) {
			logger.error("Error in retrieving all measure units", e);
			throw new RuntimeException("Error in retrieving all measure units.");
		} finally {
			ida.close();
		}

		return units;
	}

	private List<StorageElement> getAllRoomAndFreezers() throws Exception {
		List<StorageElement> storageElements = new ArrayList<StorageElement>();
		IDataAccess ida = (new DataAccessProxy())
				.getInstance(IDataAccess.HIBERNATE);
		try {
			ida.open();
			String hqlString = "from StorageElement where type in ('Room', 'Freezer')";
			List results = ida.search(hqlString);
			for (Object obj : results) {
				storageElements.add((StorageElement) obj);
			}
		} catch (Exception e) {
			logger.error("Error in retrieving all rooms and freezers", e);
			throw new RuntimeException(
					"Error in retrieving all rooms and freezers.");
		} finally {
			ida.close();
		}
		return storageElements;
	}

	/**
	 * 
	 * @return the default sample container information in a form of
	 *         ContainerInfoBean
	 */
	public ContainerInfoBean getAliquotContainerInfo() throws Exception {
		return getSampleContainerInfo();
	}

	/**
	 * Get all samples in the database
	 * 
	 * @return a list of SampleBean containing sample Ids and names
	 */
	public List<SampleBean> getAllSamples() throws Exception {
		List<SampleBean> samples = new ArrayList<SampleBean>();
		IDataAccess ida = (new DataAccessProxy())
				.getInstance(IDataAccess.HIBERNATE);

		try {
			ida.open();
			String hqlString = "select sample.id, sample.name from Sample sample";
			List results = ida.search(hqlString);
			for (Object obj : results) {
				Object[] sampleInfo = (Object[]) obj;
				samples.add(new SampleBean(StringUtils
						.convertToString(sampleInfo[0]), StringUtils
						.convertToString(sampleInfo[1])));
			}
		} catch (Exception e) {
			logger.error("Error in retrieving all sample IDs and names", e);
			throw new RuntimeException(
					"Error in retrieving all sample IDs and names");
		} finally {
			ida.close();
		}
		Collections.sort(samples, new CalabComparators.SampleBeanComparator());
		return samples;
	}

	/**
	 * Retrieve all Assay Types from the system
	 * 
	 * @return A list of all assay type
	 */
	public List getAllAssayTypes() throws Exception {
		List<String> assayTypes = new ArrayList<String>();
		IDataAccess ida = (new DataAccessProxy())
				.getInstance(IDataAccess.HIBERNATE);
		try {
			ida.open();
			String hqlString = "select assayType.name from AssayType assayType order by assayType.executeOrder";
			List results = ida.search(hqlString);
			for (Object obj : results) {
				assayTypes.add((String) obj);
			}
		} catch (Exception e) {
			logger.error("Error in retrieving all assay types", e);
			throw new RuntimeException("Error in retrieving all assay types");
		} finally {
			ida.close();
		}
		return assayTypes;
	}

	public Map<String, SortedSet<AssayBean>> getAllAssayTypeAssays()
			throws Exception {
		Map<String, SortedSet<AssayBean>> assayTypeAssays = new HashMap<String, SortedSet<AssayBean>>();
		IDataAccess ida = (new DataAccessProxy())
				.getInstance(IDataAccess.HIBERNATE);
		try {
			ida.open();
			String hqlString = "select assay.id, assay.name, assay.assayType from Assay assay";
			List results = ida.search(hqlString);
			SortedSet<AssayBean> assays = null;
			for (Object obj : results) {
				Object[] objArray = (Object[]) obj;
				AssayBean assay = new AssayBean(
						((Long) objArray[0]).toString(), (String) objArray[1],
						(String) objArray[2]);
				if (assayTypeAssays.get(assay.getAssayType()) != null) {
					assays = (SortedSet<AssayBean>) assayTypeAssays.get(assay
							.getAssayType());
				} else {
					assays = new TreeSet<AssayBean>(
							new CalabComparators.AssayBeanComparator());
					assayTypeAssays.put(assay.getAssayType(), assays);
				}
				assays.add(assay);
			}
		} catch (Exception e) {
			logger.error("Error in retrieving all assay beans. ", e);
			throw new RuntimeException("Error in retrieving all assays beans. ");
		} finally {
			ida.close();
		}
		return assayTypeAssays;
	}

	/**
	 * Retrieve all assays
	 * 
	 * @return a list of all assays in certain type
	 */
	public List<String> getAllAssignedAliquots() {
		// Detail here
		List<String> aliquots = new ArrayList<String>();
		return aliquots;
	}

	public List<AssayBean> getAllAssayBeans() throws Exception {
		List<AssayBean> assayBeans = new ArrayList<AssayBean>();
		IDataAccess ida = (new DataAccessProxy())
				.getInstance(IDataAccess.HIBERNATE);
		try {
			ida.open();
			String hqlString = "select assay.id, assay.name, assay.assayType from Assay assay";
			List results = ida.search(hqlString);
			for (Object obj : results) {
				Object[] objArray = (Object[]) obj;
				AssayBean assay = new AssayBean(
						((Long) objArray[0]).toString(), (String) objArray[1],
						(String) objArray[2]);
				assayBeans.add(assay);
			}
		} catch (Exception e) {
			logger.error("Error in retrieving all assay beans. ", e);
			throw new RuntimeException("Error in retrieving all assays beans. ");
		} finally {
			ida.close();
		}
		return assayBeans;
	}

	public List<UserBean> getAllUserBeans() throws Exception {
		List<UserBean> userBeans = new ArrayList<UserBean>();
		IDataAccess ida = (new DataAccessProxy())
				.getInstance(IDataAccess.HIBERNATE);
		try {
			ida.open();
			String hqlString = "from User user order by user.lastName";
			List results = ida.search(hqlString);
			for (Object obj : results) {
				User doUser = (User) obj;
				UserBean user = new UserBean(doUser.getLoginName(), doUser
						.getFirstName(), doUser.getLastName());
				userBeans.add(user);
			}
		} catch (Exception e) {
			logger.error("Error in retrieving all aliquot Ids and names", e);
			throw new RuntimeException(
					"Error in retrieving all aliquot Ids and names");
		} finally {
			ida.close();
		}
		return userBeans;
	}
}
