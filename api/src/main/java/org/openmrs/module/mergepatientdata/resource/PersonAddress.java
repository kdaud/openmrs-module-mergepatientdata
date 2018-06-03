package org.openmrs.module.mergepatientdata.resource;

import java.util.Date;

public class PersonAddress extends Address {
	
	private Integer personAddressId;
	
	private Person person;
	
	private Boolean preferred = false;
	
	private String address1;
	
	private String address2;
	
	private String address3;
	
	private String address4;
	
	private String address5;
	
	private String address6;
	
	private String address7;
	
	private String address8;
	
	private String address9;
	
	private String address10;
	
	private String address11;
	
	private String address12;
	
	private String address13;
	
	private String address14;
	
	private String address15;
	
	private String cityVillage;
	
	private String countyDistrict;
	
	private String stateProvince;
	
	private String country;
	
	private String postalCode;
	
	private String latitude;
	
	private String longitude;
	
	private Date startDate;
	
	private Date endDate;
	
	public PersonAddress() {
		
	}
	
	public PersonAddress(org.openmrs.PersonAddress openmrsPersonAdress) {
		this.cityVillage = openmrsPersonAdress.getCityVillage();
		this.countyDistrict = openmrsPersonAdress.getCountyDistrict();
		this.stateProvince = openmrsPersonAdress.getStateProvince();
		this.country = openmrsPersonAdress.getCountry();
		this.postalCode = openmrsPersonAdress.getPostalCode();
		this.latitude = openmrsPersonAdress.getLatitude();
		this.startDate = openmrsPersonAdress.getStartDate();
		this.endDate = openmrsPersonAdress.getEndDate();
		this.personAddressId = openmrsPersonAdress.getPersonAddressId();
		this.person = new Person(openmrsPersonAdress.getPerson());
		this.preferred = openmrsPersonAdress.getPreferred();
		this.address1 = openmrsPersonAdress.getAddress1();
		this.address2 = openmrsPersonAdress.getAddress2();
		this.address3 = openmrsPersonAdress.getAddress3();
		this.address4 = openmrsPersonAdress.getAddress4();
		this.address5 = openmrsPersonAdress.getAddress5();
		this.address6 = openmrsPersonAdress.getAddress6();
		this.address7 = openmrsPersonAdress.getAddress7();
		this.address8 = openmrsPersonAdress.getAddress8();
		this.address9 = openmrsPersonAdress.getAddress9();
		this.address10 = openmrsPersonAdress.getAddress10();
		this.address11 = openmrsPersonAdress.getAddress11();
		this.address12 = openmrsPersonAdress.getAddress12();
		this.address13 = openmrsPersonAdress.getAddress13();
		this.address14 = openmrsPersonAdress.getAddress14();
		this.address15 = openmrsPersonAdress.getAddress15();
	}
	
	public Integer getPersonAddressId() {
		return personAddressId;
	}
	
	public void setPersonAddressId(Integer personAddressId) {
		this.personAddressId = personAddressId;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Boolean getPreferred() {
		return preferred;
	}
	
	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}
	
	@Override
	public String getAddress1() {
		return address1;
	}
	
	@Override
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	@Override
	public String getAddress2() {
		return address2;
	}
	
	@Override
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	@Override
	public String getAddress3() {
		return address3;
	}
	
	@Override
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	
	@Override
	public String getAddress4() {
		return address4;
	}
	
	@Override
	public void setAddress4(String address4) {
		this.address4 = address4;
	}
	
	@Override
	public String getAddress5() {
		return address5;
	}
	
	@Override
	public void setAddress5(String address5) {
		this.address5 = address5;
	}
	
	@Override
	public String getAddress6() {
		return address6;
	}
	
	@Override
	public void setAddress6(String address6) {
		this.address6 = address6;
	}
	
	public String getAddress7() {
		return address7;
	}
	
	public void setAddress7(String address7) {
		this.address7 = address7;
	}
	
	public String getAddress8() {
		return address8;
	}
	
	public void setAddress8(String address8) {
		this.address8 = address8;
	}
	
	public String getAddress9() {
		return address9;
	}
	
	public void setAddress9(String address9) {
		this.address9 = address9;
	}
	
	public String getAddress10() {
		return address10;
	}
	
	public void setAddress10(String address10) {
		this.address10 = address10;
	}
	
	public String getAddress11() {
		return address11;
	}
	
	public void setAddress11(String address11) {
		this.address11 = address11;
	}
	
	public String getAddress12() {
		return address12;
	}
	
	public void setAddress12(String address12) {
		this.address12 = address12;
	}
	
	public String getAddress13() {
		return address13;
	}
	
	public void setAddress13(String address13) {
		this.address13 = address13;
	}
	
	public String getAddress14() {
		return address14;
	}
	
	public void setAddress14(String address14) {
		this.address14 = address14;
	}
	
	public String getAddress15() {
		return address15;
	}
	
	public void setAddress15(String address15) {
		this.address15 = address15;
	}
	
	@Override
	public String getCityVillage() {
		return cityVillage;
	}
	
	@Override
	public void setCityVillage(String cityVillage) {
		this.cityVillage = cityVillage;
	}
	
	@Override
	public String getCountyDistrict() {
		return countyDistrict;
	}
	
	@Override
	public void setCountyDistrict(String countyDistrict) {
		this.countyDistrict = countyDistrict;
	}
	
	@Override
	public String getStateProvince() {
		return stateProvince;
	}
	
	@Override
	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}
	
	@Override
	public String getCountry() {
		return country;
	}
	
	@Override
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String getPostalCode() {
		return postalCode;
	}
	
	@Override
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	@Override
	public String getLatitude() {
		return latitude;
	}
	
	@Override
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@Override
	public String getLongitude() {
		return longitude;
	}
	
	@Override
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
