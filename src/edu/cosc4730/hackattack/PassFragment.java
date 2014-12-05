package edu.cosc4730.hackattack;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PassFragment extends Fragment {
	
	MainActivity game;
	
	public PassFragment() {}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
		
		game = (MainActivity) getActivity();
        View myView = inflater.inflate(R.layout.fragment_pass, container, false);
        
        /* entering player 2's turn; increment turn integer */
        game.turn++;
        
        Button btnPass = (Button) myView.findViewById(R.id.btn_continue);
        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	swapToBoardFrag();
            }
          });
        
        return myView;
	}
	
	public void swapToBoardFrag() {
		
		BoardFragment boardFragment = new BoardFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		
		transaction.replace(R.id.fragment_container, boardFragment);
		transaction.addToBackStack(null);
		
		transaction.commit();
	}

}
