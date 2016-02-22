package com.tanlifei.download.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.tanlifei.download.DownloadManager;
import com.tanlifei.download.R;
import com.tanlifei.download.entity.DownloadStatusLevel;
import com.tanlifei.download.entity.test.DownloadEntry;
import com.tanlifei.download.notify.DataWatcher;

import java.util.ArrayList;

/**
 * 
 * @author shuwoom
 * @email 294299195@qq.com
 * @date 2015-9-2
 * @update 2015-9-2
 * @des Test download list.
 */
public class ListActivity extends Activity implements OnClickListener{
	
	private ArrayList<DownloadEntry> downloadEntries = new ArrayList<DownloadEntry>();
	
	
	private Button pauseAllBtn;
	private Button recoverAllBtn;
	private ListView listView;
    private DownloadAdapter adapter;
	
	private DataWatcher dataWatcher = new DataWatcher() {

		@Override
		public void onDataChanged(DownloadEntry downloadEntry) {

			int index = -1;
			for (int i = 0 ;i<=downloadEntries.size()-1;i++){
				if(downloadEntry.getUrl().equals(downloadEntries.get(i).getUrl())){
					downloadEntries.remove(i);
					downloadEntries.add(i, downloadEntry);
					index = i;
				}
			}
			if(index!=-1)
				adapter.updateView(index);
		}
	};
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		
		pauseAllBtn = (Button)findViewById(R.id.pause_all_btn);
		recoverAllBtn = (Button)findViewById(R.id.recover_all_btn);
		listView = (ListView) findViewById(R.id.listview);
		
		DownloadEntry entry = new DownloadEntry();
		entry.setName("少年三国志.apk");
		entry.setUrl("http://gh-game.oss-cn-hangzhou.aliyuncs.com/1435814701749842.apk");
		downloadEntries.add(entry);
		
		entry = new DownloadEntry();
		entry.setName( "放开那三国.apk");
		entry.setUrl("http://gh-game.oss-cn-hangzhou.aliyuncs.com/1437641784268948.apk");
		downloadEntries.add(entry);
		
		entry = new DownloadEntry();
		entry.setName( "去吧皮卡丘.apk");
		entry.setUrl("http://gh-game.oss-cn-hangzhou.aliyuncs.com/1437740158861375.apk");
		downloadEntries.add(entry);
		
		entry = new DownloadEntry();
		entry.setName( "X三国.apk");
		entry.setUrl("http://gh-game.oss-cn-hangzhou.aliyuncs.com/1434794302961350.apk");
		downloadEntries.add(entry);
		
		entry = new DownloadEntry();
		entry.setName( "火影忍者-忍者大师.apk");
		entry.setUrl("http://gh-game.oss-cn-hangzhou.aliyuncs.com/1435242602866100.apk");
		downloadEntries.add(entry);
        
        adapter = new DownloadAdapter();
        listView.setAdapter(adapter);
        
        pauseAllBtn.setOnClickListener(this);
        recoverAllBtn.setOnClickListener(this);
		
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
	
	class DownloadAdapter extends BaseAdapter {

        private ViewHolder holder;

        @Override
        public int getCount() {
            return downloadEntries != null ? downloadEntries.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return downloadEntries.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null || convertView.getTag() == null) {
                convertView = LayoutInflater.from(ListActivity.this).inflate(R.layout.listview_item, null);
                holder = new ViewHolder();
                holder.downloadBtn = (Button) convertView.findViewById(R.id.download_btn);
                holder.downloadBtn2 = (Button) convertView.findViewById(R.id.download_btn2);
                holder.statusText = (TextView) convertView.findViewById(R.id.show_txt);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final DownloadEntry entry = downloadEntries.get(position);
            holder.statusText.setText(entry.toString());
            holder.downloadBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	if(entry.getStatus() == DownloadStatusLevel.IDLE.value()){
        				DownloadManager.getInstance(ListActivity.this).add(entry);
        			}else if(entry.getStatus() == DownloadStatusLevel.DOWNLOADING.value()
        					||entry.getStatus() == DownloadStatusLevel.WAITING.value() ){
        				DownloadManager.getInstance(ListActivity.this).pause(entry);
        			}else if(entry.getStatus() == DownloadStatusLevel.PAUSE.value()){
        				DownloadManager.getInstance(ListActivity.this).resume(entry);
        			}else{
        				DownloadManager.getInstance(ListActivity.this).add(entry);
        			}
                }
            });
            holder.downloadBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	Intent intent = new Intent();
                	intent.setClass(ListActivity.this, DetailsActivity.class);
                	Bundle bundle = new Bundle();
                	bundle.putSerializable("bean", entry);
                	intent.putExtras(bundle);
                	startActivity(intent);
                }
            });
            return convertView;
        }
        
        class ViewHolder {
        	Button downloadBtn;
        	Button downloadBtn2;
            TextView statusText;
        }

		// 更新指定item的数据
		private void updateView(int index)
		{
			int visiblePos = listView.getFirstVisiblePosition();
			int offset = index - visiblePos;
			//Log.e("", "index="+index+"visiblePos="+visiblePos+"offset="+offset);
			// 只有在可见区域才更新
			if(offset < 0) return;

			View view = listView.getChildAt(offset);
			final DownloadEntry entry = downloadEntries.get(index);
			ViewHolder holder = (ViewHolder)view.getTag();
			//Log.e("", "id="+app.id+", name="+app.name);
			holder.statusText.setText(entry.toString());
		}
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.pause_all_btn:
			DownloadManager.getInstance(ListActivity.this).pauseAll();
			break;
		case R.id.recover_all_btn:
			DownloadManager.getInstance(ListActivity.this).recoverAll();
			break;
		}
	}



}
