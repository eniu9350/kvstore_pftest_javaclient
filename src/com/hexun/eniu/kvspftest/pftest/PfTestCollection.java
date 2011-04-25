package com.hexun.eniu.kvspftest.pftest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.hexun.eniu.kvspftest.result.Result;
import com.hexun.eniu.kvspftest.result.ResultEntry;

import tokyotyrant.RDB;

public interface PfTestCollection {

	// ------------- interfaces for users------------------------------
	void init() throws IOException;

	void run();

	void save() throws FileNotFoundException;
	
	// ------------- interfaces for developers------------------------------
	//String getKVSName();
}
