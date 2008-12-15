package com.sa.xrace.client;

import android.media.MediaPlayer;

/**
 * @author yang ye
 * @version $Id: MainPlayer.java,v 1.1 2008-11-17 07:32:26 cpan Exp $
 */

public class MainPlayer {
	public static MediaPlayer mp,mp2;
	public static boolean isBegin = true;
	public static boolean music = true;
	public static boolean  effect = true;

	public MainPlayer(MediaPlayer mp,MediaPlayer mp2){
		this.mp = mp;
		this.mp2 = mp2;
		mp.setLooping(true);
	}

}