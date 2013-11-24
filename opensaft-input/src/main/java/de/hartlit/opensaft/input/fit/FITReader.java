package de.hartlit.opensaft.input.fit;

import java.io.IOException;
import java.io.InputStream;

import com.garmin.fit.ActivityMesg;
import com.garmin.fit.ActivityMesgListener;
import com.garmin.fit.CourseMesg;
import com.garmin.fit.CourseMesgListener;
import com.garmin.fit.CoursePointMesg;
import com.garmin.fit.CoursePointMesgListener;
import com.garmin.fit.Decode;
import com.garmin.fit.DeviceSettingsMesg;
import com.garmin.fit.DeviceSettingsMesgListener;
import com.garmin.fit.EventMesg;
import com.garmin.fit.EventMesgListener;
import com.garmin.fit.File;
import com.garmin.fit.FileIdMesg;
import com.garmin.fit.FileIdMesgListener;
import com.garmin.fit.Fit;
import com.garmin.fit.FitRuntimeException;
import com.garmin.fit.Gender;
import com.garmin.fit.LapMesg;
import com.garmin.fit.LapMesgListener;
import com.garmin.fit.LengthMesg;
import com.garmin.fit.LengthMesgListener;
import com.garmin.fit.Manufacturer;
import com.garmin.fit.Mesg;
import com.garmin.fit.MesgBroadcaster;
import com.garmin.fit.MesgListener;
import com.garmin.fit.MonitoringInfoMesg;
import com.garmin.fit.MonitoringInfoMesgListener;
import com.garmin.fit.MonitoringMesg;
import com.garmin.fit.MonitoringMesgListener;
import com.garmin.fit.RecordMesg;
import com.garmin.fit.RecordMesgListener;
import com.garmin.fit.SessionMesg;
import com.garmin.fit.SessionMesgListener;
import com.garmin.fit.UserProfileMesg;
import com.garmin.fit.UserProfileMesgListener;

import de.hartlit.opensaft.common.geodetic.CoordinatesUtils;
import de.hartlit.opensaft.input.ActivityReader;
import de.hartlit.opensaft.input.ActivityReaderEvent;

/**
 * 
 * @author Manuel Hartl / hartl-it.de
 * 
 * 
 * not thread safe
 */
public class FITReader implements ActivityReader {

	private MesgBroadcaster mesgBroadcaster;
	private ActivityReaderEvent activityReaderEvent;

	private class MyDeviceSettingsMesgListener implements DeviceSettingsMesgListener {

		@Override
		public void onMesg(DeviceSettingsMesg mesg) {
			System.out.println("UTC offset: " + mesg.getUtcOffset());
		}
	}

	private class MyMesgListener implements MesgListener {

		@Override
		public void onMesg(Mesg mesg) {
			System.out.println("mesg.name: " + mesg.getName());
		}
	}

	private class MyLengthMesgListener implements LengthMesgListener {

		@Override
		public void onMesg(LengthMesg mesg) {
			System.out.print(" length:swimStroke: " + mesg.getSwimStroke());
			System.out.print(" length:avgSpeed: " + mesg.getAvgSpeed());
			System.out.println();
		}
	}

	private class MyMonitoringMesgListener implements MonitoringMesgListener, MonitoringInfoMesgListener {

		@Override
		public void onMesg(MonitoringInfoMesg mesg) {
			System.out.println("monitor.timestamp" + mesg.getTimestamp());
			System.out.println("monitor.local" + mesg.getLocalTimestamp());
		}

		@Override
		public void onMesg(MonitoringMesg mesg) {
			System.out.println("MonitoringMesg:" + mesg.getTimestamp());
		}

	}

	private class MyActivityMesgListener implements ActivityMesgListener {

		@Override
		public void onMesg(ActivityMesg mesg) {
			mesgBroadcaster.setSystemTimeOffset(mesg.getLocalTimestamp());
			System.out.println(getClass().getSimpleName() + " event: " + mesg.getEvent());
			System.out.println(getClass().getSimpleName() + " eventType: " + mesg.getEventType());
			System.out.println(getClass().getSimpleName() + " time stamp " + mesg.getTimestamp());
			System.out.println(getClass().getSimpleName() + " local timestamp: " + mesg.getLocalTimestamp());
		}
	}

	private static class MyLapMesgListener implements LapMesgListener {

		@Override
		public void onMesg(LapMesg mesg) {
			System.out.println(getClass().getSimpleName() + " timestamp " + mesg.getTimestamp());
			System.out.println(getClass().getSimpleName() + " eventType: " + mesg.getEventType());
			System.out.println(getClass().getSimpleName() + " swim stroke " + mesg.getSwimStroke());
			System.out.println(getClass().getSimpleName() + " totalDistance " + mesg.getTotalDistance());
		}
	}

	private static class MyEventMesgListener implements EventMesgListener {

		@Override
		public void onMesg(EventMesg mesg) {
			System.out.println(getClass().getSimpleName() + " event: " + mesg.getEvent());
			System.out.println(getClass().getSimpleName() + " eventType: " + mesg.getEventType());
		}

	}

	private static class MySessionMesgListener implements SessionMesgListener {

		@Override
		public void onMesg(SessionMesg mesg) {
			System.out.println(getClass().getSimpleName() + "event: " + mesg.getEvent());

		}

	}

	private static class Listener implements FileIdMesgListener, UserProfileMesgListener, CourseMesgListener,
			CoursePointMesgListener, RecordMesgListener {
		public void onMesg(FileIdMesg mesg) {
			System.out.println("File ID:");
			System.out.println("time created: " + mesg.getTimeCreated());

			if ((mesg.getType() != null) && (mesg.getType() != File.INVALID)) {
				System.out.print("   Type: ");
				System.out.println(mesg.getType().getValue());
			}

			if ((mesg.getManufacturer() != null) && (!mesg.getManufacturer().equals(Manufacturer.INVALID))) {
				System.out.print("   Manufacturer: ");
				System.out.println(mesg.getManufacturer());
			}

			if ((mesg.getProduct() != null) && (!mesg.getProduct().equals(Fit.UINT16_INVALID))) {
				System.out.print("   Product: ");
				System.out.println(mesg.getProduct());
			}

			if ((mesg.getSerialNumber() != null) && (!mesg.getSerialNumber().equals(Fit.UINT32Z_INVALID))) {
				System.out.print("   Serial Number: ");
				System.out.println(mesg.getSerialNumber());
			}

			if ((mesg.getNumber() != null) && (!mesg.getNumber().equals(Fit.UINT16_INVALID))) {
				System.out.print("   Number: ");
				System.out.println(mesg.getNumber());
			}
		}

		public void onMesg(UserProfileMesg mesg) {
			System.out.println("User profile:");

			if ((mesg.getFriendlyName() != null) && (!mesg.getFriendlyName().equals(Fit.STRING_INVALID))) {
				System.out.print("   Friendly Name: ");
				System.out.println(mesg.getFriendlyName());
			}

			if (mesg.getGender() != null) {
				if (mesg.getGender() == Gender.MALE)
					System.out.println("   Gender: Male");
				else if (mesg.getGender() == Gender.FEMALE)
					System.out.println("   Gender: Female");
			}

			if ((mesg.getAge() != null) && (!mesg.getAge().equals(Fit.UINT8_INVALID))) {
				System.out.print("   Age [years]: ");
				System.out.println(mesg.getAge());
			}

			if ((mesg.getWeight() != null) && (!mesg.getWeight().equals(Fit.FLOAT32_INVALID))) {
				System.out.print("   Weight [kg]: ");
				System.out.println(mesg.getWeight());
			}
		}

		@Override
		public void onMesg(CoursePointMesg mesg) {
			System.out.println(mesg.getPositionLong() + ":" + mesg.getPositionLong());

		}

		@Override
		public void onMesg(CourseMesg mesg) {
			System.out.println(mesg.getSport().name());
		}

		private double lastLat=-1;
		
		private double lastLong=-1;
		
		private double totalMeters=0;
		
		@Override
		public void onMesg(RecordMesg mesg) {
			// System.out.println("strokeType: " + mesg.gettrokeType());
			System.out.print(" time:" + mesg.getTimestamp());
			if (mesg.getStrokeType() != null) {
				System.out.print(" strokeType: " + mesg.getStrokeType());
			}
			// System.out.println("strokeType: " + mesg.getStrokeType());
			if (mesg.getTimestamp().getTimestamp() < 0x10000000) {
				System.out.print(" sec:" + mesg.getTimestamp().getTimestamp());
				System.out.print(" min:" + mesg.getTimestamp().getTimestamp() / 60);
				System.out.print(" hour:" + mesg.getTimestamp().getTimestamp() / 60 / 60);
			}

			if (mesg.getHeartRate() != null) {
				System.out.print(" hr:" + mesg.getHeartRate());
			}
			double currentLat = CoordinatesUtils.semicircles2degrees(mesg.getPositionLat());
			if (mesg.getPositionLat() != null) {
				System.out.print(" lat=" + currentLat);
			}
			double currentLong = CoordinatesUtils.semicircles2degrees(mesg.getPositionLong());
			if (mesg.getPositionLong() != null) {
				System.out.print(" long=" + currentLong);
			}
			if (lastLat != -1) {
				double meters = CoordinatesUtils.vincenty_km(lastLat, lastLong, currentLat, currentLong);
				totalMeters += lastLat == -1 ? 0 : meters;
				System.out.print(" distance " + meters);
			}
			System.out.print(" distanceTotal " + totalMeters);
			lastLat=currentLat;
			lastLong=currentLong;
			if (mesg.getCalories() != null) {
				System.out.print(" calories:" + mesg.getCalories());
			}
			if (mesg.getDistance() != null) {
				System.out.print(" distance:" + mesg.getDistance());
			}
			if (mesg.getGpsAccuracy() != null) {
				System.out.print(" gpsAccuracy:" + mesg.getGpsAccuracy());
			}
			if (mesg.getAltitude() != null) {
				System.out.print(" altidude:" + mesg.getAltitude());
			}
			System.out.print(" speed:" + mesg.getSpeed()); // m/s
			System.out.println();
		}

	}

	@Override
	public void parse(InputStream inputStream, ActivityReaderEvent activityReaderEvent) throws IOException {
		this.activityReaderEvent=activityReaderEvent;
		
		Decode decode = new Decode();
//		decode.setSystemTimeOffset(60 * 60);
		mesgBroadcaster = new MesgBroadcaster(decode);
		Listener listener = new Listener();

		// try {
		// if (!Decode.checkIntegrity((InputStream) in))
		// throw new RuntimeException("FIT file integrity failed.");
		// } finally {
		// try {
		// in.close();
		// } catch (java.io.IOException e) {
		// throw new RuntimeException(e);
		// }
		// }t

		mesgBroadcaster.addListener((FileIdMesgListener) listener);
		mesgBroadcaster.addListener((UserProfileMesgListener) listener);
		mesgBroadcaster.addListener((CourseMesgListener) listener);
		mesgBroadcaster.addListener((CoursePointMesgListener) listener);
		mesgBroadcaster.addListener((RecordMesgListener) listener);
		mesgBroadcaster.addListener(new MySessionMesgListener());
		mesgBroadcaster.addListener(new MyLapMesgListener());
		mesgBroadcaster.addListener(new MyLengthMesgListener());
		mesgBroadcaster.addListener(new MyEventMesgListener());
		mesgBroadcaster.addListener(new MyActivityMesgListener());
		mesgBroadcaster.addListener(new MyDeviceSettingsMesgListener());
		mesgBroadcaster.addListener((MonitoringMesgListener) new MyMonitoringMesgListener());
		mesgBroadcaster.addListener((MonitoringInfoMesgListener) new MyMonitoringMesgListener());
		mesgBroadcaster.addListener(new MyMesgListener());
		try {
			mesgBroadcaster.run(inputStream);
		} catch (FitRuntimeException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}


}
