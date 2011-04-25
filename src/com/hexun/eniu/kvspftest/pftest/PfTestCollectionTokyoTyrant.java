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

public class PfTestCollectionTokyoTyrant implements PfTestCollection {
	private List<PfTest> colls;
	private String fnCfg;
	private String fnTestInput;
	private String fnResult;

	// public void addTest(PfTest t) {
	// colls.add(t); // order is preserved?
	// }

	private String ipSrv;
	private int portSrv;

	private void loadCfgFile() throws IOException {
		// process cfg file
		File fCfg = new File(fnCfg);
		Properties props = new Properties();
		props.load(new FileInputStream(fCfg));

		// assume single server
		ipSrv = props.getProperty("server");
		portSrv = Integer.parseInt(props.getProperty("port"));

		// process test input to generate tests
		BufferedReader br = new BufferedReader(new FileReader(fnTestInput));
		String line;
		while ((line = br.readLine()) != null) {
			PfTest t = new PfTest();
			t.loadLine(line);
			colls.add(t); // order is preserved?
		}
	}

	private RDB db;

	/**
	 * connect to remote
	 */
	private void connect() {
		// create the object
		db = new RDB();

		// connect to the server
		db.open(new InetSocketAddress(ipSrv, portSrv)); // 1978
	}

	// ------------- interfaces ------------------------------
	PfTestCollectionTokyoTyrant(String fnCfg, String fnTestInput,
			String fnResult) {
		colls = new ArrayList<PfTest>();
		this.fnCfg = fnCfg;
		this.fnTestInput = fnTestInput;
		this.fnResult = fnResult;
	}

	public void init() throws IOException {
		loadCfgFile();
		connect();
	}

	private Result result = new Result("TokyoTyrant");

	public void run() {
		int size = colls.size(); // coll size
		int[] reps = new int[size];
		int[] counts = new int[size];

		for (int i = 0; i < size; i++) {
			counts[i] = colls.get(i).getReps();
			reps[i] = colls.get(i).getRounds();
		}

		long tStart, tEnd;

		for (int h = 0; h < size; h++) {
			for (int i = 0; i < reps[h]; i++) {
				String keys[] = new String[counts[h]];
				String values[] = new String[counts[h]];
				for (int j = 0; j < counts[h]; j++) {
					keys[j] = "key" + j + "." + counts[h] + "puts.";
					values[j] = String.valueOf(j);
				}
				tStart = System.currentTimeMillis();
				for (int j = 0; j < counts[h]; j++) {
					db.put(keys[j], values[j]);
				}
				tEnd = System.currentTimeMillis();
				// String key = "log." + counts[h] + "puts.round" + i + ".";
				// String value = String.valueOf(tEnd - tStart);
				// db.put(key, value);
				// System.out.println("key=" + key);
				// System.out.println("value=" + value);

				result.add(new ResultEntry(counts[h], tEnd - tStart));
			}
		}
	}

	public void save() throws FileNotFoundException {
		result.save(fnResult);
	}

	public static void main(String[] args) throws IOException {
		PfTestCollectionTokyoTyrant pftc = new PfTestCollectionTokyoTyrant(
				"F:\\prog\\kvstestdata\\config.txt",
				"F:\\prog\\kvstestdata\\input.txt",
				"F:\\prog\\kvstestdata\\result.txt");
		pftc.init();
		pftc.run();
		pftc.save();
	}

}
