package com.restaurant.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "Reservation")
public class ReservationDetails {

	@Id
	String userid;
	String bookingType;
	String name;
	String contactNo;
	int people;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	Date dateAntTime;

	public ReservationDetails() {
		super();
	}

	public ReservationDetails(String userid, String bookingType, String name, String contactNo, int people, Date date) {
		super();
		this.userid = userid;
		this.bookingType = bookingType;
		this.name = name;
		this.contactNo = contactNo;
		this.people = people;
		this.dateAntTime = dateAntTime;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	public Date getDateAndTime() {
		return dateAntTime;
	}

	public void setDateAndTime(Date date) {
		this.dateAntTime = date;
	}

	@Override
	public String toString() {
		return "Reservation [userid=" + userid + ", bookingType=" + bookingType + ", name=" + name + ", contactNo="
				+ contactNo + ", people=" + people + ", date=" + dateAntTime + "]";
	}

}
