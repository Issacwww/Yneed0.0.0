package com.yneed.yneed;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
public class mes_ielts extends Activity implements OnItemClickListener{

    private ListView lvNews;
    private NewsAdapter adapter;
    private List<News> newsList;

    //此处需要修改为自己的服务器地址
    public static final String GET_NEWS_URL = "http:/192.168.56.1/YneedDemo/getNewsJSON.php";

    private Handler getNewsHandler = new Handler(){

        public void handleMessage(android.os.Message msg) {
            String jsonData = (String) msg.obj;
            System.out.println(jsonData);
            try {
                JSONArray jsonArray = new JSONArray(jsonData);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    String title = object.getString("title");
                    String desc = object.getString("desc");
                    String time = object.getString("time");
                    String content_url = object.getString("content_url");
                    String pic_url = object.getString("pic_url");
                    newsList.add(new News(title, desc, time, content_url, pic_url));
                }
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mes_ielts);

        lvNews = (ListView) findViewById(R.id.mesi);
        newsList = new ArrayList<News>();
        adapter = new NewsAdapter(this, newsList);

        lvNews.setAdapter(adapter);
        lvNews.setOnItemClickListener(this);


        HttpUtils.getNewsJSON(GET_NEWS_URL, getNewsHandler);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
        News news = newsList.get(position);
        Intent intent = new Intent(this, BrowseNewsActivity.class);
        intent.putExtra("content_url", news.getContent_url());
        startActivity(intent);
    }

}

