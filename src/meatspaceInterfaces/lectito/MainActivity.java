package meatspaceInterfaces.lectito;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String TAG = "QuizActivity";
	private static final String KEY_INDEX = "index";
	boolean paused = false; // needs a way to be controlled (pause button)
	int mCurrentIndex = 0; // index of which word in the Words[]
	Words[] mWordsArray = new Words[] { // needs a way to be loaded
			new Words("testing1"), 
			new Words("testing2"), 
			new Words("testing3"), 
			new Words("testing4"), 
			new Words("testing5"), 
			new Words("testing6"), 
			new Words("testing7"), 
			new Words("testing8"), 
			new Words("testing9"), 
			new Words("still") 
	};
	TextView mWordTextView; // the view on screen of the word
	private Handler handler = new Handler();
	private Runnable updateWord = new Runnable() {
		@Override
		public void run() { // this runnable updates the word
			Log.d(TAG, "updating word for question #" + mCurrentIndex, new Exception());
			mCurrentIndex = (mCurrentIndex + 1); // increment index
			mWordTextView.setText(mWordsArray[mCurrentIndex].getWord());
			if (mCurrentIndex < mWordsArray.length-1) // check if end of array
				handler.postDelayed(updateWord, myPeriod);
		}
	};
	int myDelay = 1000; // needs a way to be set by user
	int myPeriod = 100; // needs a way to be set by user

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) //check for continue of previous session
			mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
		setContentView(R.layout.activity_main);
		mWordTextView = (TextView) findViewById(R.id.word_text_view);
		handler.postDelayed(updateWord, myDelay); //do it
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		Log.i(TAG, "onSaveInstanceState");
		savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.i(TAG, "onRestoreInstanceState");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.d(TAG, "onStarted() called");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(TAG, "onPause() called");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume() called");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.d(TAG, "onStop() called");
	}

} // end class