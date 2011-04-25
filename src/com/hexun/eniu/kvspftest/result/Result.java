package com.hexun.eniu.kvspftest.result;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Result {
	private List<ResultEntry> list;
	private String kvsName;

	public Result(String kvsName) {
		list = new ArrayList<ResultEntry>();
		this.kvsName = kvsName;
	}

	public void save(String fnResult) throws FileNotFoundException {
		File fResult = new File(fnResult);
		FileOutputStream fos = new FileOutputStream(fResult, true);
		PrintWriter out = new PrintWriter(fos);

		out.println(new Date());
		out.println("kvstore name: " + kvsName);
		for (int i = 0; i < list.size(); i++) {
			out.println(list.get(i).toString());
		}
		out.println();

		out.close();
	}

	public void add(ResultEntry re) {
		list.add(re);
	}

	public List<ResultEntry> getList() {
		return list;
	}

	public void setList(List<ResultEntry> list) {
		this.list = list;
	}

	public String getKvsName() {
		return kvsName;
	}

	public void setKvsName(String kvsName) {
		this.kvsName = kvsName;
	}
}
