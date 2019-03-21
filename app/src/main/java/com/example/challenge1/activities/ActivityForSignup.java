package com.example.challenge1.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.challenge1.R;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityForSignup extends AppCompatActivity {

	Button signup;
	TextView heading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_for_signup);
		signup = findViewById(R.id.signup);
		heading = findViewById(R.id.heading);

		/*signup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				heading.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.warning));
				heading.setText("*Passwords do not match");
			}
		});*/
	}
}
