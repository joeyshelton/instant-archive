package com.joeyshelton.instantarchive;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.*;
import java.util.regex.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        // Handle text being sent to this activity
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent);
            }
        }
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            // Get URL from user's "Share" action
            String sharedUrl = extractURL(sharedText);

            // Build URL to trigger archive.today archive process
            String encodedUrl = encodeUrl(sharedUrl);
            String archiveUrl = "https://archive.today/?run=1&url=" + encodedUrl;

            // Open URL in the default browser
            Uri uri = Uri.parse(archiveUrl);
            Intent browser_intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browser_intent);
        }
    }

    String encodeUrl(String url) {
        try {
            return URLEncoder.encode(url, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

    String extractURL(String str){
        // Regular Expression to extract URL from the string
        String regex = "\\b((?:https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:, .;]*[-a-zA-Z0-9+&@#/%=~_|])";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        if (m.find())
            return str.substring(m.start(0), m.end(0));
        return "";
    }

}