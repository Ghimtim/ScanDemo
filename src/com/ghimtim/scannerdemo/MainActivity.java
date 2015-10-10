package com.ghimtim.scannerdemo;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.R.integer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    
	private Button bt1;
	private ProgressDialog mProgressDialog;
	private Handler mHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt1=(Button) findViewById(R.id.bt_scan);
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
				integrator.initiateScan();
			}
		});
		mHandler = new Handler(){
			   @Override
	            public void handleMessage(Message msg) {
	               // TODO Auto-generated method stub
	                super.handleMessage(msg);
	                BookInfo book= (BookInfo)msg.obj;
	                //进度条消失
	                mProgressDialog.dismiss();
	                Intent intent=new Intent(MainActivity.this,BookInfoDetailActivity.class);
	                //Bundle bd=new Bundle();
	                //bundle.putSerializable(key,object);
	                //bd.putSerializable(BookInfo.class.getName(),book);
	                //intent.putExtras(bd);
	                intent.putExtra(BookInfo.class.getName(),book);
	                startActivity(intent);
	            }


		};
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);
		IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if((scanResult == null) || (scanResult.getContents() == null)){
			//Toast.makeText(this,"scan success!",Toast.LENGTH_LONG).show();
			Log.i("LJT", "user cancel scan by pressing back hardkey");
			return;
		}
		//下载耗时，显示进度条；
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setMessage("通信中~");
		mProgressDialog.show();
		
		//启动下载线程
/*		DownloadThread thread = new DownloadThread(BookAPI.URL_ISBN_BASE + scanResult.getContents());
		thread.start();*/
		 String urlstr="https://api.douban.com/v2/book/isbn/"+scanResult.getContents();
	        Log.i("OUTPUT",urlstr);
	        //扫到ISBN后，启动下载线程下载图书信息
	        new DownloadThread(urlstr).start();
	}
	private class DownloadThread extends Thread {
		private String mURL;
		
		public DownloadThread(String url){
			super();
			mURL = url;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String result=Utils.Download(mURL);
            Log.i("OUTPUT", "download over");
            BookInfo book=new Utils().parseBookInfo(result);
            Log.i("OUTPUT", "parse over");
            Log.i("OUTPUT",book.getmSummary()+book.getmAuthor());
            //给主线程UI界面发消息，提醒下载信息，解析信息完毕
			Message msg = Message.obtain();
			msg.obj = book;
			mHandler.sendMessage(msg);
			Log.i("OUTPUT","send over");

		}
		
	}

/*	private static class DownloadHandler extends Handler {
		private MainActivity mActivity;
		@SuppressWarnings("unused")
		public DownloadHandler(MainActivity activity){
			super();
			mActivity = activity;
		}
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if((msg.obj == null)||(mActivity.mProgressDialog == null)||(!mActivity.mProgressDialog.isShowing())) {
				return;
			}
			mActivity.mProgressDialog.dismiss();
			NetResponse response = (NetResponse) msg.obj;
			if(response.getmCode()!=BookAPI.RESPONSE_CODE_SUCCEED){
				Toast.makeText(mActivity, "["+response.getmCode()+"]:"+mActivity.getString((Integer)response.getmMessage()), Toast.LENGTH_LONG).show();
				
			}else {
				mActivity.startBookInfoDetailActivty((BookInfo) response.getmMessage());
			}
		}
		
	}

	public void startBookInfoDetailActivty(BookInfo bookinfo) {
		// TODO Auto-generated method stub
		if(bookinfo == null){
			return;
		}
		Intent it = new Intent(this,BookInfoDetailActivity.class);
		it.putExtra(BookInfo.class.getName(), bookinfo);
		startActivity(it);
	}
	*/
}
