/**
 * The Processing-Syphon library allows to create Syphon clients
 * and servers in a Processing sketch to share frames with other
 * applications. It only works on MacOSX and requires the P3D
 * renderer.
 *
 * (c) 2011-2012
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 * 
 * @author		Andres Colubri http://interfaze.info/
 * @modified	02/21/2012
 * @version		0.4
 */

package codeanticode.syphon;

import processing.core.*;
import processing.opengl.*;
import jsyphon.*;

/**
 * Syphon server class. It broadcasts the textures encapsulated in 
 * PImage objects.
 *
 */
public class SyphonServer {
	protected PApplet parent;
	protected PGraphicsOpenGL pgl;
	protected JSyphonServer server;
	
  /**
   * Constructor that sets server with specified name.
   * 
   * @param parent
   * @param serverName
   */	
  public SyphonServer(PApplet parent, String serverName) {
    this.parent = parent;
    pgl = (PGraphicsOpenGL)parent.g;
    
    Syphon.init();
    
    server = new JSyphonServer();
    server.initWithName(serverName);
  }
	
  /**
   * Returns true if this server is bound to any
   * client.
   * 
   * @return boolean 
   */    
  public boolean hasClients() {
    return server.hasClients();
  }	
	
  /**
   * Sends the source image to the bound client.
   * 
   * @param source
   */ 	
  public void sendImage(PImage source) {
    PTexture tex = pgl.getTexture(source);
    if (tex != null) {
      server.publishFrameTexture(tex.glID, tex.glTarget, 
                                 0, 0, tex.glWidth, tex.glHeight, 
                                 tex.glWidth, tex.glHeight, false);
    } else {
      PGraphics.showWarning("Texture is null");
    }
  }	
  
  /**
   * Stops the server.
   * 
   */  
  public void stop() {
    server.stop();
  }  
}

