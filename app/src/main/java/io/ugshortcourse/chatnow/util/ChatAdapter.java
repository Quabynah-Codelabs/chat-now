package io.ugshortcourse.chatnow.util;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import io.ugshortcourse.chatnow.R;
import io.ugshortcourse.chatnow.data.User;

public class ChatAdapter extends FirestoreRecyclerAdapter {
	private static final String TAG = "ChatAdapter";
	
	private Activity host;
	private FirestoreRecyclerOptions<User> options;
	
	public ChatAdapter(Activity host, @NonNull FirestoreRecyclerOptions<User> options) {
		super(options);
		this.host = host;
		this.options = options;
	}
	
	private static final int TYPE_EMPTY = -1;
	private static final int TYPE_CHAT = 0;
	
	@Override
	public int getItemViewType(int position) {
		if (options.getSnapshots().isEmpty()) return TYPE_EMPTY;
		return TYPE_CHAT;
	}
	
	@Override
	protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull Object model) {
		if (getItemViewType(position) == TYPE_CHAT) {
			if (holder instanceof ChatViewHolder) {
				Object o = getSnapshots().get(position);
				((ChatViewHolder) holder).username.setText(o.toString());
			}
		}
	}
	
	@NonNull
	@Override
	public Object getItem(int position) {
		Object o = getSnapshots().get(position);
		if (o instanceof User) {
			Log.d(TAG, "getItem: " + o.toString());
		}
		return super.getItem(position);
	}
	
	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		switch (i) {
			case TYPE_EMPTY:
				return new EmptyViewHolder(host.getLayoutInflater().inflate(R.layout.item_empty, viewGroup, false));
			case TYPE_CHAT:
				return new ChatViewHolder(host.getLayoutInflater().inflate(R.layout.item_chat, viewGroup, false));
			default:
				return null;
		}
		
	}
	
	public class EmptyViewHolder extends RecyclerView.ViewHolder {
		
		public EmptyViewHolder(@NonNull View itemView) {
			super(itemView);
			
		}
	}
	
	public class ChatViewHolder extends RecyclerView.ViewHolder {
		TextView username;
		
		public ChatViewHolder(@NonNull View itemView) {
			super(itemView);
			this.username = itemView.findViewById(R.id.user_name);
		}
	}
}
