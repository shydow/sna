package com.tangpian.sna.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@PersistenceCapable
public class FetchElement {
	public static final int TYPE_USER = 1;

	public static final int SOURCE_GPLUS = 1;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.UUIDSTRING)
	private String id;

	@Persistent
	private String userNo;

	@Persistent
	private int type;

	@Persistent
	private int source;

	@Persistent
	private Date fetchTime;

	public FetchElement(String userNo, int type, int source) {
		this.userNo = userNo;
		this.type = type;
		this.source = source;
	}

	public FetchElement(String userNo, int type, int source, Date date) {
		this.userNo = userNo;
		this.type = type;
		this.source = source;
		this.fetchTime = date;
	}

	public FetchElement() {

	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getFetchTime() {
		return fetchTime;
	}

	public void setFetchTime(Date fetchTime) {
		this.fetchTime = fetchTime;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (!(obj instanceof FetchElement)) {
			return false;
		}
		FetchElement element = (FetchElement) obj;

		return new EqualsBuilder()
				.append(this.getUserNo(), element.getUserNo())
				.append(this.getSource(), element.getSource())
				.append(this.getType(), element.getType()).isEquals();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(FetchElement.class).toString();
	}
}
