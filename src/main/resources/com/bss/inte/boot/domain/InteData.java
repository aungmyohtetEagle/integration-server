package com.bss.inte.boot.domain;

public class InteData {

	private Long itemId;
	private Long itemHeaderId;
	private String postingDate;
	private String reference;
	private Long branchId;
	private String currency;
	private Integer glCode;
	private String journalCode;
	private String label;
	private Double credit;
	private Double debit;
	private Long custId;
	private String loanAcct;
	private String savingsAcct;
	private String transactionType;
	private Integer sendErp;
	private String recordDate;

	public InteData(

			Long itemId, Long itemHeaderId, String postingDate, String reference, Long branchId, String currency,
			Integer glCode, String journalCode, String label, Double credit, Double debit, Long custId, String loanAcct,
			String savingsAcct, String transactionType, Integer sendErp, String recordDate) {
		this.itemId = itemId;
		this.itemHeaderId = itemHeaderId;
		this.postingDate = postingDate;
		this.reference = reference;
		this.branchId = branchId;
		this.currency = currency;
		this.glCode = glCode;
		this.journalCode = journalCode;
		this.label = label;
		this.credit = credit;
		this.debit = debit;
		this.custId = custId;
		this.loanAcct = loanAcct;
		this.savingsAcct = savingsAcct;
		this.transactionType = transactionType;
		this.sendErp = sendErp;
		this.recordDate = recordDate;
	}

	public Long getItemId() {
		return itemId;
	}

	public Long getItemHeaderId() {
		return itemHeaderId;
	}

	public String getPostingDate() {
		return postingDate;
	}

	public String getReference() {
		return reference;
	}

	public Long getBranchId() {
		return branchId;
	}

	public String getCurrency() {
		return currency;
	}

	public Integer getGlCode() {
		return glCode;
	}

	public String getJournalCode() {
		return journalCode;
	}

	public String getLabel() {
		return label;
	}

	public Double getCredit() {
		return credit;
	}

	public Double getDebit() {
		return debit;
	}

	public Long getCustId() {
		return custId;
	}

	public String getLoanAcct() {
		return loanAcct;
	}

	public String getSavingsAcct() {
		return savingsAcct;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public Integer getSendErp() {
		return sendErp;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public void setItemHeaderId(Long itemHeaderId) {
		this.itemHeaderId = itemHeaderId;
	}

	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setGlCode(Integer glCode) {
		this.glCode = glCode;
	}

	public void setJournalCode(String journalCode) {
		this.journalCode = journalCode;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	public void setDebit(Double debit) {
		this.debit = debit;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public void setLoanAcct(String loanAcct) {
		this.loanAcct = loanAcct;
	}

	public void setSavingsAcct(String savingsAcct) {
		this.savingsAcct = savingsAcct;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public void setSendErp(Integer sendErp) {
		this.sendErp = sendErp;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

}
