package com.tangpian.sna.component.gplus.domain;

import javax.persistence.Entity;

import com.tangpian.sna.model.Content;

@Entity
public class GplusContent extends Content {
	private String title;

	private String geocode;

	private String address;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGeocode() {
		return geocode;
	}

	public void setGeocode(String geocode) {
		this.geocode = geocode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
