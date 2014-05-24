package com.demo.mongodb.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "MeterEnergyMonthlyUsage")
public class MeterEnergyMonthlyUsage {

	@Id
	private String id;
	private String energyUsageId;
	private String accountProductServiceDetailId;
	private String totalUsage;
	private String previousMsmt;
	private String currentMsmt;

	public MeterEnergyMonthlyUsage() {
	}

	public MeterEnergyMonthlyUsage(String energyUsageId,
			String accountProductServiceDetailId, String totalUsage,
			String previousMsmt, String currentMsmt) {
		super();
		this.energyUsageId = energyUsageId;
		this.accountProductServiceDetailId = accountProductServiceDetailId;
		this.totalUsage = totalUsage;
		this.previousMsmt = previousMsmt;
		this.currentMsmt = currentMsmt;
	}

	public MeterEnergyMonthlyUsage(String id, String energyUsageId,
			String accountProductServiceDetailId, String totalUsage,
			String previousMsmt, String currentMsmt) {
		super();
		this.id = id;
		this.energyUsageId = energyUsageId;
		this.accountProductServiceDetailId = accountProductServiceDetailId;
		this.totalUsage = totalUsage;
		this.previousMsmt = previousMsmt;
		this.currentMsmt = currentMsmt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEnergyUsageId() {
		return energyUsageId;
	}

	public void setEnergyUsageId(String energyUsageId) {
		this.energyUsageId = energyUsageId;
	}

	public String getAccountProductServiceDetailId() {
		return accountProductServiceDetailId;
	}

	public void setAccountProductServiceDetailId(
			String accountProductServiceDetailId) {
		this.accountProductServiceDetailId = accountProductServiceDetailId;
	}

	public String getTotalUsage() {
		return totalUsage;
	}

	public void setTotalUsage(String totalUsage) {
		this.totalUsage = totalUsage;
	}

	public String getPreviousMsmt() {
		return previousMsmt;
	}

	public void setPreviousMsmt(String previousMsmt) {
		this.previousMsmt = previousMsmt;
	}

	public String getCurrentMsmt() {
		return currentMsmt;
	}

	public void setCurrentMsmt(String currentMsmt) {
		this.currentMsmt = currentMsmt;
	}

}
