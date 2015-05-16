package com.hindu.news.commonui;
/**
 * Created by Mageswari on 16-05-2015.
 */
public class CommonRowData {
	private Object details;
	private Object subDetails;
	private Object moreSubDetails;

	public Object getMoreSubDetails() {
		return moreSubDetails;
	}

	public void setMoreSubDetails(Object moreSubDetails) {
		this.moreSubDetails = moreSubDetails;
	}

	private String head1 = "";
	private String head2 = "";
	private String head3 = "";
	private String head4 = "";
	private String subHead1 = "";
	private String subHead2 = "";
	private String subHead3 = "";
	private String subHead4 = "";

	public void setDetails(Object details) {
		this.details = details;
	}

	public void setHead1(String head1) {
		this.head1 = head1;
	}

	public void setHead2(String head2) {
		this.head2 = head2;
	}

	public void setHead3(String head3) {
		this.head3 = head3;
	}

	public void setSubHead1(String subHead1) {
		this.subHead1 = subHead1;
	}

	public void setSubHead2(String subHead2) {
		this.subHead2 = subHead2;
	}

	public void setSubHead3(String subHead3) {
		this.subHead3 = subHead3;
	}

	public Object getDetails() {
		return this.details;
	}

	public String getHead1() {
		return this.head1;
	}

	public String getHead2() {
		return this.head2;
	}

	public String getHead3() {
		return this.head3;
	}

	public String getSubHead1() {
		return this.subHead1;
	}

	public String getSubHead2() {
		return this.subHead2;
	}

	public String getSubHead3() {
		return this.subHead3;
	}

	public String getHead4() {
		return head4;
	}

	public void setHead4(String head4) {
		this.head4 = head4;
	}

	public String getSubHead4() {
		return subHead4;
	}

	public void setSubHead4(String subHead4) {
		this.subHead4 = subHead4;
	}

	public Object getSubDetails() {
		return subDetails;
	}

	public void setSubDetails(Object subDetails) {
		this.subDetails = subDetails;
	}

}
