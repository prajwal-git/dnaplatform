package com.dna.backend.gridtask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.dna.backend.mail.EmailSenderService;
import com.dna.backend.modle.Attendance;
import com.dna.backend.modle.Meeting;
import com.dna.backend.repository.AttendanceRepository;
import com.dna.backend.repository.MeetingRepository;
import com.dna.backend.repository.UserRepository;

/**
 * 
 * This class will return the list of meetings that are scheduled for a
 * particular date from meeting scheduler, fetch the participant's details from
 * Zoom and insert those details into attendance module
 * 
 * Method run() works with meeting scheduler and returns the list of meetings
 * that are set for scheduled for a particular date. This method gets refreshed
 * every hour so that if any of the scheduled meeting is added in the list, gets
 * updated here.
 * 
 * Method taskManager() works with zoom and gets all the participant details.
 * This method gets refreshed every 15 minutes so that once the scheduled
 * meeting has started, this method will contact zoom after 15 minutes (assuming
 * all the participants has joined the meeting) and ask for the participant's
 * details. Here, RestTemplate has been implemented to call zoom.
 * 
 * The return details from the zoom is in JSON String format. This result is
 * converted into JSON object and using JSONArray, the respective participant
 * details are iterated and fetched.
 * 
 * Based on the participant's email address, this method also verifies with the
 * registered user email address. If participant's email address is not
 * verified, an email will be sent to the ADMIN saying unregistered participant
 * is detected in the particular meeting.
 * 
 * Once all the participants are verified, the details of the participants are
 * then inserted into the Attendance module.
 * 
 * @author Nischal Dahal
 * @since 2022/02/18
 * 
 **/

@Component
public class MeetingGridTask {

	@Autowired
	private AttendanceRepository attendanceRepo;

	@Autowired
	private MeetingRepository meetingRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private EmailSenderService emailService;

	List<Meeting> meetings;
	String nameMeeting;
	Date timeMeeting;

	@Scheduled(cron = "${grid.meetingcron}") // runs every 1 hour,settings in application.properties
	public void run() {
		meetings = (List<Meeting>) meetingRepo.findAll(); // return all the meeting details from meeting scheduler

	}

	@Scheduled(cron = "${grid.taskmanagercron}") // runs every 15 minutes, settings in application.properties
	public void taskManager() {
		try {

			long diff = 0;

			for (Meeting m : meetings) {

				Calendar meetingTime = Calendar.getInstance();
				meetingTime.setTime(m.getTime());// setting date and time of current meeting in the list

				TimeZone tz = TimeZone.getDefault();
				TimeZone.setDefault(TimeZone.getTimeZone("CST"));// setting system time zone as CST

				Calendar now = Calendar.getInstance(); // current system's date and time
				long currentTime = now.getTimeInMillis(); // converting current time in milliseconds
				long currentMeetingStartTime = meetingTime.getTimeInMillis();// converting current meeting time in
																				// milliseconds

				diff = currentTime - currentMeetingStartTime;// difference between current time and meeting time in
																// milliseconds

				if ((diff >= 15 * 60 * 1000) && (diff < 16 * 60 * 1000)) { // 15 minutes after the meeting has been
																			// started
																			// limit is 1 minute to call zoom so that
																			// this
																			// meeting will not call zoom again
																			// in next iteration. Zoom gets called based
																			// on the
																			// difference in current time and meeting
																			// time.
					nameMeeting = m.getMeetingName();
					timeMeeting = m.getTime();

					RestTemplate rt = new RestTemplate();// consumption, consuming restful services using RestTemplate
					HttpHeaders requestHeaders = new HttpHeaders();//
					requestHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
					HttpEntity<String> entity = new HttpEntity<String>("parameters", requestHeaders);
					ResponseEntity<String> st = rt.exchange("http://localhost:8081/zoom/" + nameMeeting, HttpMethod.GET,
							entity, String.class);

					String jsonString = st.getBody();// return result from zoom is in JSON String format

					JSONObject obj = new JSONObject(jsonString);// converting JSON String into JSON object
					JSONArray meetingParticipants = obj.getJSONArray("participants");// getting all elements of array
																						// participants using JSONArray

					for (int i = 0; i < meetingParticipants.length(); i++) {
						JSONObject participantsDetail = meetingParticipants.getJSONObject(i);
						String participantsEmail = (String) participantsDetail.get("email");// fetch respective
																							// participant's email

						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String userName = (String) participantsDetail.get("userName");// fetch respective participant's
																						// user name

						String getMeetingDate = (String) participantsDetail.get("meetingDate");// result meeting date is
																								// in
																								// string format

						Date meetingDate = sdf.parse(getMeetingDate);// convert String into Date

						String getLoginTime = (String) participantsDetail.get("loginTime");
						Date loginTime = sdf.parse(getLoginTime);

						String getLogoutTime = (String) participantsDetail.get("logoutTime");
						Date logoutTime = sdf.parse(getLogoutTime);

						if (userRepo.getEmail(participantsEmail) == null) { // if participant email is not verified,
																			// email will be sent to ADMIN

							String messageBody = "Unregistered user name " + userName + " with email address "
									+ participantsEmail + " detected in meeting " + nameMeeting + " held on "
									+ timeMeeting;

							emailService.sendMessage("mailreceivertospringboot@gmail.com", "Alert", messageBody);// EmailSenderService
																													// class
																													// gets
																													// called
																													// here

						} else { // if statement is false

							Attendance attendance = new Attendance(userName, meetingDate, loginTime, logoutTime);// setting
																													// the
																													// details
																													// in
																													// Attendance
																													// module
							attendanceRepo.save(attendance);// Adding the details in the Attendance table
						}
					}
				}
			}

		} catch (Exception e) {

		}

	}
}
