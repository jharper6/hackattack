package edu.cosc4730.hackattack;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BoardFragment extends Fragment {
	
	public BoardFragment() {}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_board, container, false);
        // TODO create buttons
        Button test = (Button) myView.findViewById(R.id.btn_test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              swapFrag();
            }
          });
        
        // TODO add listeners
        myView.setBackgroundColor(Color.BLUE);
        return myView;
	}
	
	public void swapFrag() {
		
		PassFragment passFragment = new PassFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		
		transaction.replace(R.id.fragment_container, passFragment);
		transaction.addToBackStack(null);
		
		transaction.commit();
	}

}
