package ctec.soundandvideo.controller;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class VideoActivity extends ActionBarActivity
{
	private VideoView myPlayer;
	private Button returnButton;
	private MediaController myVideoController;
	private Uri videoLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		
		myPlayer = (VideoView) findViewById(R.id.videoView);
		returnButton = (Button) findViewById(R.id.returnButton);
		videoLocation = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.party);
		myVideoController = new MediaController(this);
		
		//Prepares the video
		setupMedia();
		setupListeners();
		
	}
	
	private void setupMedia()
	{
		myPlayer.setMediaController(myVideoController);
		myPlayer.setVideoURI(videoLocation);
	}
	
	private void setupListeners()
	{
		returnButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void OnClick(View currentView)
			{
				Intent myIntent = new Intent(currentView.getContext(), SoundActivity.class);
				startActivityForResult(myIntent, 0);
				
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.video, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
