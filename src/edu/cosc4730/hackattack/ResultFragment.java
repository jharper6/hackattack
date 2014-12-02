package edu.cosc4730.hackattack;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ResultFragment extends Fragment {

	public ResultFragment() {}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_result, container, false);
        myView.setBackgroundColor(Color.CYAN);
        
        TextView output = (TextView) myView.findViewById(R.id.output);
        
        // are these even necessary?
        TextView tv1 = (TextView) myView.findViewById(R.id.tv_pass1);
        TextView tv2 = (TextView) myView.findViewById(R.id.tv_pass2);
        
        Button btnPass = (Button) myView.findViewById(R.id.btn_continue2);
        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              swapFrag();
            }
          });
        
        return myView;
	}
	
	public void swapFrag() {
		
		BoardFragment boardFragment = new BoardFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		
		transaction.replace(R.id.fragment_container, boardFragment);
		transaction.addToBackStack(null);
		
		transaction.commit();
	}
	
}
