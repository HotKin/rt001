package com.rt.entity.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="user_info")
@Data
public class UserInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4751850167790460759L;

	@Id
	@Column(name="uid")
	private Integer uId;
	
	@Column(name="name",length=255)
	private String name;
	
	@Column(name="password",length=255)
	private String password;
	
	@Column(name="salt",length=255)
	private String salt;
	
	@Column(name="state")
	private Integer state;
	
	@Column(name="username",length=255)
	private String userName;
}
