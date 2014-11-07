package ctec.soundandvideo.controller;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class SoundActivity extends ActionBarActivity implements Runnable
{
	private Button startButton;
	private Button stopButton;
	private Button pauseButton;
	private Button videoButton;
	private MediaPlayer soundPlayer;
	private SeekBar soundSeekBar;
	private Thread soundThread;
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sound);
		
		startButton = (Button) findViewById(R.id.playButton);
		pauseButton = (Button) findViewById(R.id.pauseButton);
		stopButton = (Button) findViewById(R.id.stopButton);
		videoButton = (Button) findViewById(R.id.videoButton);
		
		soundSeekBar = (SeekBar) findViewById(R.id.soundSeekBar);
		soundPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.heylisten);
		
		
		setupListeners();
		
		soundThread = new Thread(this);
		soundThread.start();
	}

	private void setupListeners()
	{
		startButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				soundPlayer.start();
				soundPlayer = MediaPlayer.create(getBaseContext(), R.raw.heylisten);
			}
		});
		pauseButton.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				soundPlayer.pause();
				soundPlayer = MediaPlayer.create(getBaseContext(), R.raw.heylisten);
			}
			
		});
		stopButton.setOnClickListener(new View.OnClickListener()

		{
			
			@Override
			public void onClick(View v)
			{
				soundPlayer.stop();
				soundPlayer = MediaPlayer.create(getBaseContext(), R.raw.heylisten);
			}
		});
		
		
		videoButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent returnIntent = new Intent();
				setResult(RESULT_OK, returnIntent);
				finish();
			}
		});
		
		
		soundSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
		{
			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar)
			{}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				if(fromUser)
				{
					soundPlayer.seekTo(progress);
				}
			}
		});
	}
	
	public void run()
	{
		int currentPosition = 0;
		int soundTotal = soundPlayer.getDuration();
		soundSeekBar.setMax(soundTotal);
		
		while ( soundPlayer != null && currentPosition < soundTotal)
		{
			try 
			{
				
				Thread.sleep(50);
				currentPosition = soundPlayer.getCurrentPosition();
			}
			catch(InterruptedException soundException)
			{
				return;
			}
			catch(Exception otherException)
			{
				return;
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sound, menu);
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
