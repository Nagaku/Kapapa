/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kapapa.kapapa.service;

import edu.kapapa.kapapa.entity.Memo;
import edu.kapapa.kapapa.error.MemoException;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface MemoDao {
	
	public void insertMemo(Memo memo) throws MemoException;
	public void updateMemo(Memo memo) throws MemoException;
	public void deleteMemo(int idMemo) throws MemoException;
	public void deleteAll() throws MemoException;
	public Memo getMemo(String mataPelajaran) throws MemoException;
	public List<Memo> selectAllMemo() throws MemoException;
	
	
}
