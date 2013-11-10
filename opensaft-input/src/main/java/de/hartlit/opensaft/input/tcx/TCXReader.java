package de.hartlit.opensaft.input.tcx;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.garmin.schemas.tcxv2.TrainingCenterDatabaseT;

/**
 * 
 * @author Manuel Hartl / hartl-it.de
 * 
 */
public class TCXReader {

	public TCXReader(InputStream inputStream) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(TrainingCenterDatabaseT.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Object object = unmarshaller.unmarshal(inputStream);
		JAXBElement<TrainingCenterDatabaseT> jaxbTrainingCenterDatabaseT = (JAXBElement<TrainingCenterDatabaseT>) object;
		TrainingCenterDatabaseT trainingCenterDatabaseT = jaxbTrainingCenterDatabaseT.getValue();
		System.out.println(trainingCenterDatabaseT.getActivities().getActivity().get(0).getSport());
	}

}
