package com.example.jkubala.exercise04_b;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listview;
    private String mediaPath;
    private List<FileWrapper> songs = new ArrayList<FileWrapper>();
    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.musicList);
        mediaPath = Environment.getExternalStorageDirectory().getPath() + "/documents";

        // item listener
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(songs.get(position).getFile().getAbsolutePath());
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                    Toast.makeText(getApplicationContext(), "Play: " + songs.get(position).getFile().getName(), Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(getBaseContext(), "Cannot start audio !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        LoadSongsTask task = new LoadSongsTask();
        task.execute();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mediaPlayer.isPlaying()) mediaPlayer.reset();
    }

    // Use AsyncTask to read all mp3 file names
    private class LoadSongsTask extends AsyncTask<Void, String, Void> {

        private List<FileWrapper> loadedSongs = new ArrayList<FileWrapper>();

        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_LONG).show();
        }

        protected Void doInBackground(Void... url) {
            updateSongListRecursive(new File(mediaPath));
            return null;
        }

        public void updateSongListRecursive(File path) {
            if (path == null)
                return;

            if (path.isDirectory()) {
                for (int i = 0; i < path.listFiles().length; i++) {
                    File file = path.listFiles()[i];
                    updateSongListRecursive(file);
                }
            } else {
                String name = path.getAbsolutePath();
                publishProgress(name);
                if (name.endsWith(".mp3")) {
                    loadedSongs.add(new FileWrapper(path));
                }
            }
        }

        protected void onPostExecute(Void args) {
            ArrayAdapter<FileWrapper> songList = new ArrayAdapter<FileWrapper>(MainActivity.this, android.R.layout.simple_list_item_1, loadedSongs);
            listview.setAdapter(songList);
            songs = loadedSongs;

            Toast.makeText(getApplicationContext(), "Songs=" + songs.size(), Toast.LENGTH_LONG).show();
        }
    }

    private class FileWrapper {

        private File file;

        public FileWrapper(File file) {
            this.file = file;
        }

        @Override
        public String toString() {
            return "" + file.getName();
        }

        public File getFile() {
            return file;
        }
    }
}
