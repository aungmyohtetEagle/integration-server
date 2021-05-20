package com.bss.inte.boot.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.bss.inte.boot.domain.InteData;

public class BuffWriter {

	List<InteData> list;

	public BuffWriter(List<InteData> list) {
		this.list = list;
	}

	public void writeFileUsingBufferedWritter() throws IOException {
		/*
		 * StringBuffer SQL = new
		 * StringBuffer("INSERT INTO inte_mifos_journal_temp VALUES ");
		 * 
		 * for (InteData data : list) { SQL.append("( "); SQL.append(data.getGlId() +
		 * ", "); SQL.append(data.getBranchId() + ", "); SQL.append(data.getCustId() +
		 * ", "); SQL.append(data.getLoanId() + ", "); SQL.append(data.getSavingsId() +
		 * ", "); SQL.append(" 'INSERT', 1, current_timestamp()),");
		 * 
		 * }
		 * 
		 * SQL.setLength(SQL.length() - 1);
		 * 
		 * File file = new File("C:/test.txt"); BufferedWriter bw = new
		 * BufferedWriter(new FileWriter(file)); bw.write(SQL.toString()); bw.close();
		 */

	}

	public String composeForSQL() {

		/*
		 * StringBuffer SQL = new
		 * StringBuffer("INSERT INTO inte_mifos_journal_temp VALUES ");
		 * 
		 * int i = 0; for (InteData data : list) { SQL.append("( ");
		 * SQL.append(data.getGlId() + ", "); SQL.append(data.getBranchId() + ", ");
		 * SQL.append(data.getCustId() + ", "); SQL.append(data.getLoanId() + ", ");
		 * SQL.append(data.getSavingsId() + ", ");
		 * SQL.append(" 'INSERT', 1, current_timestamp),"); i++;
		 * 
		 * if(i == 30000) break;
		 * 
		 * }
		 * 
		 * SQL.setLength(SQL.length() - 1); return SQL.toString();
		 */
		
		return "";
	}

}
