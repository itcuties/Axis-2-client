package com.itcuties.ws.service;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.junit.Assert;
import org.junit.Test;

import com.itcuties.serivces.AddHighScoreDocument;
import com.itcuties.serivces.AddHighScoreDocument.AddHighScore;
import com.itcuties.serivces.GetHighScoresDocument;
import com.itcuties.serivces.GetHighScoresDocument.GetHighScores;
import com.itcuties.serivces.GetHighScoresForNicknameDocument;
import com.itcuties.serivces.GetHighScoresForNicknameDocument.GetHighScoresForNickname;
import com.itcuties.serivces.GetHighScoresForNicknameResponseDocument;
import com.itcuties.serivces.GetHighScoresForNicknameResponseDocument.GetHighScoresForNicknameResponse;
import com.itcuties.serivces.GetHighScoresResponseDocument;
import com.itcuties.serivces.GetHighScoresResponseDocument.GetHighScoresResponse;
import com.itcuties.serivces.GetHighScoresSizeDocument;
import com.itcuties.serivces.GetHighScoresSizeDocument.GetHighScoresSize;
import com.itcuties.serivces.GetHighScoresSizeResponseDocument;
import com.itcuties.serivces.GetHighScoresSizeResponseDocument.GetHighScoresSizeResponse;
import com.itcuties.serivces.xsd.HighScore;
import com.itcuties.ws.HighScoreServiceStub;

public class HighScoreServiceTest {

	@Test
	public void testGetHighScores() {

		HighScoreServiceStub stub = null;
		try {
			stub = new HighScoreServiceStub();
		} catch (AxisFault e) {
			e.printStackTrace();
			Assert.fail();
		}

		Assert.assertNotNull(stub);

		GetHighScoresDocument requestDoc = GetHighScoresDocument.Factory.newInstance();
		GetHighScores request = GetHighScores.Factory.newInstance();

		requestDoc.setGetHighScores(request);

		GetHighScoresResponseDocument responseDoc = null;
		try {
			responseDoc = stub.getHighScores(requestDoc);
		} catch (RemoteException e) {
			e.printStackTrace();
			Assert.fail();
		}

		Assert.assertNotNull(responseDoc);

		GetHighScoresResponse response = responseDoc.getGetHighScoresResponse();

		Assert.assertNotNull(response);

		HighScore[] result = response.getReturnArray();

		Assert.assertNotNull(result);
		Assert.assertTrue(result.length > 0);

		for (HighScore hs : result) {
			System.out.println(hs.getNickname() + " scores " + hs.getScore());
		}
	}

	@Test
	public void testAddHighScore() {
		
		HighScoreServiceStub stub = null;
		try {
			stub = new HighScoreServiceStub();
		} catch (AxisFault e) {
			e.printStackTrace();
			Assert.fail();
		}

		Assert.assertNotNull(stub);

		AddHighScoreDocument requestDoc = AddHighScoreDocument.Factory.newInstance();

		AddHighScore request = AddHighScore.Factory.newInstance();

		HighScore newHighScore = HighScore.Factory.newInstance();
		newHighScore.setNickname("hero");
		newHighScore.setScore(299);

		request.setScore(newHighScore);

		requestDoc.setAddHighScore(request);

		try {
			stub.addHighScore(requestDoc);
		} catch (RemoteException e) {
			e.printStackTrace();
			Assert.fail();
		}

		Assert.assertTrue(true);
	}

	@Test
	public void testGetHighScoresForNickname() {
		
		HighScoreServiceStub stub = null;
		try {
			stub = new HighScoreServiceStub();
		} catch (AxisFault e) {
			e.printStackTrace();
			Assert.fail();
		}

		Assert.assertNotNull(stub);

		GetHighScoresForNicknameDocument requestDoc = GetHighScoresForNicknameDocument.Factory.newInstance();
		
		GetHighScoresForNickname request = GetHighScoresForNickname.Factory.newInstance();
		
		request.setNickname("person3");
		
		requestDoc.setGetHighScoresForNickname(request);
		
		GetHighScoresForNicknameResponseDocument responseDoc = null;
		try {
			responseDoc = stub.getHighScoresForNickname(requestDoc);
		} catch (RemoteException e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		Assert.assertNotNull(responseDoc);
		
		GetHighScoresForNicknameResponse response = responseDoc.getGetHighScoresForNicknameResponse();
		
		Assert.assertNotNull(response);
		
		HighScore[] result = response.getReturnArray();
		
		Assert.assertNotNull(result);
		Assert.assertTrue(result.length > 0);
		
		for (HighScore hs : result) {
			System.out.println(hs.getNickname() + " scores " + hs.getScore());
		}
	}
	
	@Test
	public void getHighScoresSize() {
		HighScoreServiceStub stub = null;
		try {
			stub = new HighScoreServiceStub();
		} catch (AxisFault e) {
			e.printStackTrace();
			Assert.fail();
		}

		Assert.assertNotNull(stub);
		
		GetHighScoresSizeDocument requestDoc = GetHighScoresSizeDocument.Factory.newInstance();
		
		GetHighScoresSize request = GetHighScoresSize.Factory.newInstance();
		
		requestDoc.setGetHighScoresSize(request);				
		
		GetHighScoresSizeResponseDocument responseDoc = null;
		try {
			responseDoc = stub.getHighScoresSize(requestDoc);
		} catch (RemoteException e) {			
			e.printStackTrace();
			Assert.fail();
		}
		
		Assert.assertNotNull(responseDoc);
		
		GetHighScoresSizeResponse response = responseDoc.getGetHighScoresSizeResponse();
		
		Assert.assertNotNull(response);
		
		Assert.assertTrue(response.getReturn() > 0);
		
		System.out.println("There is "+response.getReturn()+" highscores on the list.");
	}
}
