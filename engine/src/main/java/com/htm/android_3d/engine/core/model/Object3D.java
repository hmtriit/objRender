package com.htm.android_3d.engine.core.model;

public interface Object3D {

	void draw(Object3DData obj, float[] pMatrix, float[] vMatrix, int textureId, float[] lightPosInEyeSpace);

	void draw(Object3DData obj, float[] pMatrix, float[] vMatrix, int textureId, float[] lightPosInEyeSpace, float[] colorMask);

	void draw(Object3DData obj, float[] pMatrix, float[] vMatrix, int drawType, int drawSize, int textureId, float[] lightPosInEyeSpace);

	void draw(Object3DData obj, float[] pMatrix, float[] vMatrix, int drawType, int drawSize, int textureId, float[]
			lightPosInEyeSpace, float[] colorMask);
}