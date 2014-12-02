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
	
	Button test, prob1, prob2;
	UnitView oppUnitsA, oppUnitsB, playerUnitsA, playerUnitsB;
	
	public BoardFragment() {}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
		
        View myView = inflater.inflate(R.layout.fragment_board, container, false);
        
        test = (Button) myView.findViewById(R.id.btn_test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              swapFrag();
            }
          });
        
        /* the buttons that represent the problems */
        prob1 = (Button) myView.findViewById(R.id.btn_prob1);
        prob2 = (Button) myView.findViewById(R.id.btn_prob2);
        
        /* the views to which unit bmps are drawn */
        oppUnitsA = (UnitView) myView.findViewById(R.id.unitview1);
        oppUnitsB = (UnitView) myView.findViewById(R.id.unitview2);
        playerUnitsA = (UnitView) myView.findViewById(R.id.unitview3);
        playerUnitsB = (UnitView) myView.findViewById(R.id.unitview4);
        
        myView.setBackgroundColor(Color.BLUE); //test
        return myView;
	}
	
	public void swapFrag() {
		
		ResultFragment resultFragment = new ResultFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		
		transaction.replace(R.id.fragment_container, resultFragment);
		transaction.addToBackStack(null);
		
		transaction.commit();
	}

}
