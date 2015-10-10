package com.ghimtim.scannerdemo;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class BookInfo implements Parcelable{
    

	private String mTitle = "";
	private Bitmap mCover;
	private String mAuthor = "";
	private String mPublisher = "";
	private String mPublishDate = "";
	private String mISBN = "";
	private String mSummary = "";
	
	public static final Parcelable.Creator<BookInfo> CREATOR = new Creator<BookInfo>() {
		@Override
		public BookInfo createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			BookInfo bookinfo = new BookInfo();
			bookinfo.mTitle = source.readString();
			bookinfo.mCover = source.readParcelable(Bitmap.class.getClassLoader());
			bookinfo.mAuthor= source.readString();
			bookinfo.mPublisher = source.readString();
			bookinfo.mPublishDate = source.readString();
			bookinfo.mISBN = source.readString();
			bookinfo.mSummary = source.readString();
			return bookinfo;
		}

		@Override
		public BookInfo[] newArray(int size) {
			// TODO Auto-generated method stub
			return new BookInfo[size];
		}
	};
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
	    dest.writeString(mTitle);
	    dest.writeParcelable(mCover, flags);
	    dest.writeString(mAuthor);
	    dest.writeString(mPublisher);
	    dest.writeString(mPublishDate);
	    dest.writeString(mISBN);
	    dest.writeString(mSummary);
	}
	
	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public Bitmap getmCover() {
		return mCover;
	}

	public void setmCover(Bitmap mCover) {
		this.mCover = mCover;
	}

	public String getmAuthor() {
		return mAuthor;
	}

	public void setmAuthor(String mAuthor) {
		this.mAuthor = mAuthor;
	}

	public String getmPublisher() {
		return mPublisher;
	}

	public void setmPublisher(String mPublisher) {
		this.mPublisher = mPublisher;
	}

	public String getmPublishDate() {
		return mPublishDate;
	}

	public void setmPublishDate(String mPublishDate) {
		this.mPublishDate = mPublishDate;
	}

	public String getmISBN() {
		return mISBN;
	}

	public void setmISBN(String mISBN) {
		this.mISBN = mISBN;
	}

	public String getmSummary() {
		return mSummary;
	}

	public void setmSummary(String mSummary) {
		this.mSummary = mSummary;
	}

}
