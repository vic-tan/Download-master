package com.tanlifei.download.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.tanlifei.download.DownloadManager;
import com.tanlifei.download.R;
import com.tanlifei.download.entity.test.DownloadEntry;
import com.tanlifei.download.notify.DataWatcher;

/**
 * 
 * @author shuwoom
 * @email 294299195@qq.com
 * @date 2015-9-2
 * @update 2015-9-2
 * @des Test one download task.
 */
public class DetailsActivity extends Activity implements OnClickListener{
	

	private DownloadEntry entry = new DownloadEntry();
	
	private Button addBtn;
	private Button cancelBtn;
	private Button pauseBtn;
	private Button resumeBtn;
	
	private TextView showText;
	
	private DataWatcher dataWatcher = new DataWatcher() {

		@Override
		public void onDataChanged(DownloadEntry data) {
			if(data.getUrl().equals(entry.getUrl())){
				entry = data;
				showText.setText(entry.toString());
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		entry = (DownloadEntry) getIntent().getSerializableExtra("bean");
		showText = (TextView)findViewById(R.id.show_text);
		addBtn = (Button)findViewById(R.id.add_btn);
		cancelBtn = (Button)findViewById(R.id.cancel_btn);
		pauseBtn = (Button)findViewById(R.id.pause_btn);
		resumeBtn = (Button)findViewById(R.id.resume_btn);
		
		addBtn.setOnClickListener(this);
		cancelBtn.setOnClickListener(this);
		pauseBtn.setOnClickListener(this);
		resumeBtn.setOnClickListener(this);
		
	}
	
	@Override
    protected void onResume() {
        super.onResume();
		DownloadManager.getInstance(this).addObserver(dataWatcher);
    }
	
	@Override
    protected void onPause() {
        super.onPause();
        DownloadManager.getInstance(this).removeObserver(dataWatcher);
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.add_btn:
			DownloadManager.getInstance(DetailsActivity.this).add(entry);
			break;
		case R.id.pause_btn:
			DownloadManager.getInstance(DetailsActivity.this).pause(entry);
			break;
		case R.id.resume_btn:
			DownloadManager.getInstance(DetailsActivity.this).resume(entry);
			break;
		case R.id.cancel_btn:
			DownloadManager.getInstance(DetailsActivity.this).cancel(entry);
			break;
		}
	}

}
