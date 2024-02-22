package com.htm.android_3d.engine.util.android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES32;
import android.opengl.GLES32;
import android.opengl.GLUtils;
import android.util.Log;

import java.io.InputStream;

public final class GLUtil {

	private static final String TAG = "GLUtil";

	private GLUtil() {

	}

	/**
	 * Helper function to compile and link a program.
	 * 
	 * @param vertexShaderHandle
	 *            An OpenGL handle to an already-compiled vertex shader.
	 * @param fragmentShaderHandle
	 *            An OpenGL handle to an already-compiled fragment shader.
	 * @param attributes
	 *            Attributes that need to be bound to the program.
	 * @return An OpenGL handle to the program.
	 */
	public static int createAndLinkProgram(final int vertexShaderHandle, final int fragmentShaderHandle,
			final String[] attributes) {
		int programHandle = GLES32.glCreateProgram();

		if (programHandle != 0) {
			// Bind the vertex shader to the program.
			GLES32.glAttachShader(programHandle, vertexShaderHandle);

			// Bind the fragment shader to the program.
			GLES32.glAttachShader(programHandle, fragmentShaderHandle);

			// Bind attributes
			if (attributes != null) {
				final int size = attributes.length;
				for (int i = 0; i < size; i++) {
					GLES32.glBindAttribLocation(programHandle, i, attributes[i]);
				}
			}

			// Link the two shaders together into a program.
			GLES32.glLinkProgram(programHandle);

			// Get the link status.
			final int[] linkStatus = new int[1];
			GLES32.glGetProgramiv(programHandle, GLES32.GL_LINK_STATUS, linkStatus, 0);

			// If the link failed, delete the program.
			if (linkStatus[0] == 0) {
				Log.e(TAG, "Error compiling program: " + GLES32.glGetProgramInfoLog(programHandle));
				GLES32.glDeleteProgram(programHandle);
				programHandle = 0;
			}
		}

		if (programHandle == 0) {
			throw new RuntimeException("Error creating program.");
		}

		return programHandle;
	}

	/**
	 * Utility method for compiling a OpenGL shader.
	 * 
	 * <p>
	 * <strong>Note:</strong> When developing shaders, use the checkGlError() method to debug shader coding errors.
	 * </p>
	 * 
	 * @param type
	 *            - Vertex or fragment shader type.
	 * @param shaderCode
	 *            - String containing the shader code.
	 * @return - Returns an id for the shader.
	 */
	public static int loadShader(int type, String shaderCode) {

		// create a vertex shader type (GLES32.GL_VERTEX_SHADER)
		// or a fragment shader type (GLES32.GL_FRAGMENT_SHADER)
		int shader = GLES32.glCreateShader(type);

		// add the source code to the shader and compile it
		GLES32.glShaderSource(shader, shaderCode);
		GLES32.glCompileShader(shader);

		int[] compiled = new int[1];
		GLES32.glGetShaderiv(shader, GLES32.GL_COMPILE_STATUS, compiled, 0);
		Log.i("GLUtil", "Shader compilation info: " + GLES32.glGetShaderInfoLog(shader));
		if (compiled[0] == 0) {
			Log.e("GLUtil", "Shader error: " + GLES32.glGetShaderInfoLog(shader) + "\n" + shaderCode);
			GLES32.glDeleteShader(shader);
		}

		return shader;
	}

	public static int[] loadTexture(final InputStream is, final InputStream emissiveIs) {
		Log.v("GLUtil", "Loading texture from stream...");
		// TODO: refactor code to support more than 2 textures
//		final int[] textureHandle = new int[1];
//
//		GLES32.glGenTextures(1, textureHandle, 0);
//		GLUtil.checkGlError("glGenTextures");
//		if (textureHandle[0] == 0) {
//			throw new RuntimeException("Error loading texture.");
//		}
//
//		Log.v("GLUtil", "Handler: " + textureHandle[0]);
//
//		final BitmapFactory.Options options = new BitmapFactory.Options();
//		// By default, Android applies pre-scaling to bitmaps depending on the resolution of your device and which
//		// resource folder you placed the image in. We don’t want Android to scale our bitmap at all, so to be sure,
//		// we set inScaled to false.
//		options.inScaled = false;
//
//		// Read in the resource
//		final Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);
//		if (bitmap == null) {
//			throw new RuntimeException("couldnt load bitmap");
//		}
//
//		// Bind to the texture in OpenGL
//		GLES32.glBindTexture(GLES32.GL_TEXTURE_2D, textureHandle[0]);
//		GLUtil.checkGlError("glBindTexture");
//		GLUtils.texImage2D(GLES32.GL_TEXTURE_2D, 0, bitmap, 0);
//		GLUtil.checkGlError("texImage2D");
//		bitmap.recycle();
//		GLES32.glGenerateMipmap(GLES32.GL_TEXTURE_2D);
//
//		Log.v("GLUtil", "Loaded texture ok");
//		return textureHandle[0];

		final int[] textureHandle = new int[2];

		GLES32.glGenTextures(2, textureHandle, 0);
		GLUtil.checkGlError("glGenTextures");
		if (textureHandle[0] == 0) {
			throw new RuntimeException("Error loading texture.");
		}

		Log.v("GLUtil", "Handler: " + textureHandle[0]);

		final BitmapFactory.Options options = new BitmapFactory.Options();
		// By default, Android applies pre-scaling to bitmaps depending on the resolution of your device and which
		// resource folder you placed the image in. We don’t want Android to scale our bitmap at all, so to be sure,
		// we set inScaled to false.
		options.inScaled = false;

		// Read in the resource
		final Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);
		if (bitmap == null) {
			throw new RuntimeException("couldnt load bitmap");
		}

		// Bind to the texture in OpenGL
		GLES32.glBindTexture(GLES32.GL_TEXTURE_2D, textureHandle[0]);
		GLUtil.checkGlError("glBindTexture");
		GLUtils.texImage2D(GLES32.GL_TEXTURE_2D, 0, bitmap, 0);
		GLUtil.checkGlError("texImage2D");
		bitmap.recycle();
		GLES32.glGenerateMipmap(GLES32.GL_TEXTURE_2D);

		if (emissiveIs != null){
			final Bitmap emissiveBitmap = BitmapFactory.decodeStream(emissiveIs, null, options);
			GLES32.glBindTexture(GLES32.GL_TEXTURE_2D, textureHandle[1]);
			GLUtil.checkGlError("glBindTexture");
			GLUtils.texImage2D(GLES32.GL_TEXTURE_2D, 0, emissiveBitmap, 0);
			GLUtil.checkGlError("texImage2D");
			emissiveBitmap.recycle();
			GLES32.glGenerateMipmap(GLES32.GL_TEXTURE_2D);
		}

		Log.v("GLUtil", "Loaded texture ok");
		return textureHandle;
	}

	/**
	 * Utility method for debugging OpenGL calls. Provide the name of the call just after making it:
	 * 
	 * <pre>
	 * mColorHandle = GLES32.glGetUniformLocation(mProgram, &quot;vColor&quot;);
	 * MyGLRenderer.checkGlError(&quot;glGetUniformLocation&quot;);
	 * </pre>
	 * 
	 * If the operation is not successful, the check throws an error.
	 * 
	 * @param glOperation
	 *            - Name of the OpenGL call to check.
	 */
	public static boolean checkGlError(String glOperation) {
		int glError;
		boolean error = false;
		while ((glError = GLES32.glGetError()) != GLES32.GL_NO_ERROR) {
			Log.e(TAG, glOperation + ": glError " + glError);
			error = true;
			// throw new RuntimeException(glOperation + ": glError " + error);
		}
		return error;
	}
}
