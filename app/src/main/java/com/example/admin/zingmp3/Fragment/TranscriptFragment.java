package com.example.admin.zingmp3.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.zingmp3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TranscriptFragment extends Fragment {

     View view;
     TextView tvTranscript;
    public TranscriptFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_transcript, container, false);
         tvTranscript = view.findViewById(R.id.tvTranscrip);
        return view;
    }

}
