package edu.cosc4730.hackattack;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PassFragment extends Fragment {
	
	public PassFragment() {}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_board, container, false);
        // TODO create buttons
        // TODO add listeners
        myView.setBackgroundColor(Color.RED);
        return myView;
	}

}
