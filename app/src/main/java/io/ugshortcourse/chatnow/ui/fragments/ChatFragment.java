package io.ugshortcourse.chatnow.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;

import io.ugshortcourse.chatnow.R;
import io.ugshortcourse.chatnow.data.User;
import io.ugshortcourse.chatnow.util.ChatAdapter;

public class ChatFragment extends Fragment {
	private Toolbar toolbar;
	private RecyclerView chatList;
	private ChatAdapter adapter;
	
	
	public ChatFragment() {
	}
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_chat, container, false);
	}
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		toolbar = view.findViewById(R.id.toolbar);
		chatList = view.findViewById(R.id.chat_list);
		
		super.onViewCreated(view, savedInstanceState);
		((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
		FirebaseFirestore firestore = FirebaseFirestore.getInstance();
		FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
				.setTimestampsInSnapshotsEnabled(true)
				.build();
		firestore.setFirestoreSettings(settings);
		
		Query query = firestore.collection("chat_now_users")
				.orderBy("timestamp", Query.Direction.ASCENDING).limit(100);
		FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
				.setQuery(query, User.class)
				.build();
		
		adapter = new ChatAdapter(requireActivity(), options);
		LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
		chatList.setLayoutManager(layoutManager);
		chatList.addItemDecoration(new DividerItemDecoration(requireContext(), layoutManager.getOrientation()));
		chatList.setItemAnimator(new DefaultItemAnimator());
		chatList.setHasFixedSize(true);
		chatList.setAdapter(adapter);
		
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		//todo: inflate menu
		
	}
	
	@Override
	public void onStart() {
		super.onStart();
		adapter.startListening();
	}
	
	@Override
	public void onStop() {
		super.onStop();
		adapter.stopListening();
	}
}
