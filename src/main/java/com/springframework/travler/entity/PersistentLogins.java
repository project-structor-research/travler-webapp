package com.springframework.travler.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "persistent_logins")
public class PersistentLogins {

	@Id
	private String series;
	private String username;
	private String token;
	private Date lastUsed;
}
