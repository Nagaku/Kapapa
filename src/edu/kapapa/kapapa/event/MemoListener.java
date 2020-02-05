/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kapapa.kapapa.event;

import edu.kapapa.kapapa.entity.Memo;
import edu.kapapa.kapapa.model.MemoModel;

/**
 *
 * @author ASUS
 */
public interface MemoListener {
	public void OnMemoChange(MemoModel model);
	public void OnMemoInsert(Memo memo);
	public void OnMemoDelete();
	public void OnMemoUpdate(Memo memo);

	

	
	
	
}
