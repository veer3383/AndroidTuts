package com.mes.tuts.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {

    private FirebaseListAdapter<MessageModel> fbadapter;
    private FirebaseAuth mAuth;
    private FirebaseAnalytics mAnaly;
    private ImageView send;
    private ListView chatlist;
    private EditText messagebox;
    private FirebaseDatabase mFdb;
    private String loggedinUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mAuth = FirebaseAuth.getInstance();
        mFdb = FirebaseDatabase.getInstance();
        mAnaly = FirebaseAnalytics.getInstance(this);

        send =(ImageView) findViewById(R.id.submit_button);
        chatlist =(ListView) findViewById(R.id.list_of_message);
        messagebox =(EditText)findViewById(R.id.emojicon_edit_text);

        checkuser();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFdb.getReference().push().setValue(new MessageModel(messagebox.getText().toString(),loggedinUser));
                messagebox.setText("");
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID,loggedinUser);
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "message");
                mAnaly.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
            }
        });
    }

    private void checkuser() {
        if (mAuth.getCurrentUser() == null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        } else {
            loggedinUser = mAuth.getCurrentUser().getPhoneNumber();
            Toast.makeText(this, "Welcome "+ loggedinUser, Toast.LENGTH_SHORT).show();
        }
        loadMessages();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.logout)
        {
            mAuth.signOut();
            checkuser();
        }
        return true;
    }

    private void loadMessages() {
        fbadapter = new FirebaseListAdapter<MessageModel>(this,MessageModel.class,R.layout.list_item,mFdb.getReference()) {
            @Override
            protected void populateView(View v, MessageModel model, int position) {
                TextView messageText,messageUser,messageTime;
                messageText =(TextView)v.findViewById(R.id.message_text);
                messageTime =(TextView)v.findViewById(R.id.message_time);
                messageUser =(TextView)v.findViewById(R.id.message_user);

                messageText.setText(model.getMsgText());
                messageUser.setText(model.getMsgUser());
                messageTime.setText(DateFormat.format("dd-MM-yyy (HH:mm:ss)",model.getMsgTime()));
            }

        };
        chatlist.setAdapter(fbadapter);
    }
}
