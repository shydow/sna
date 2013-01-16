package com.tangpian.sna.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.tangpian.sna.component.gplus.domain.GplusContent;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Content {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@OneToOne
	private Profile profile;

	private String contentNo;

	/*
	 * 内容数据
	 */
	@Lob
	private String note;

	private Date published;

	private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getContentNo() {
		return contentNo;
	}

	public void setContentNo(String contentNo) {
		this.contentNo = contentNo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}

		if (!(obj instanceof GplusContent)) {
			return false;
		}

		GplusContent that = (GplusContent) obj;
		return new EqualsBuilder().append(this.getContentNo(),
				that.getContentNo()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getContentNo()).hashCode();
	}

}
