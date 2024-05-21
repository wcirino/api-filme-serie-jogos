package com.apifilmeseries.dto;

import java.io.Serializable;

public class loginDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private  String login;
	private  String email;
	private  int ativo;
	private  String ultimologin;
	
	public loginDTO() {
		super();
	}
	
	public loginDTO(String login, String email, int ativo, String ultimologin) {
		super();
		this.login = login;
		this.email = email;
		this.ativo = ativo;
		this.ultimologin = ultimologin;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public String getUltimologin() {
		return ultimologin;
	}

	public void setUltimologin(String ultimologin) {
		this.ultimologin = ultimologin;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ativo;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((ultimologin == null) ? 0 : ultimologin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		loginDTO other = (loginDTO) obj;
		if (ativo != other.ativo)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (ultimologin == null) {
			if (other.ultimologin != null)
				return false;
		} else if (!ultimologin.equals(other.ultimologin))
			return false;
		return true;
	}
	
	
	

}
