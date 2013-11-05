/*
 *  Copyright 2013, Katsuhisa Maruyama (maru@jtool.org)
 */

package org.jtool.codeforest.ui.shape;

import java.awt.Image;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.TexCoordGeneration;
import javax.media.j3d.Texture2D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector4f;

import com.sun.j3d.utils.geometry.Box;

public class Ground extends AbstractShape {
    
    private static Texture2D ground_texture;
    
    private Appearance appearance;
    
    private boolean hasRiver = false;
    
    private double width;
    
    private double depth;
    
    private double height;
    
    public Ground(double width, double depth, double height) {
        this.width = width;
        this.depth = depth;
        this.height = height;
        if (ground_texture == null) {
            Image image = AbstractShape.getAWTImage("grass");
            ground_texture = AbstractShape.createTexture(image);
        }
    }
    
    public void setRiver(boolean bool) {
        hasRiver = bool;
    }
    
    protected void setAppearance() {
        appearance = new Appearance();
        appearance.setTexture(ground_texture);
        
        TransparencyAttributes attr = new TransparencyAttributes();
        attr.setTransparency(0.9f);
        appearance.setTransparencyAttributes(attr);
        TexCoordGeneration texgen =
                new TexCoordGeneration(TexCoordGeneration.EYE_LINEAR,
                    TexCoordGeneration.TEXTURE_COORDINATE_2,
                    new Vector4f(1.0f, 0.0f, 0.0f, 0.0f),
                    new Vector4f(0.0f, 0.0f, 1.0f, 0.0f));
        appearance.setTexCoordGeneration(texgen);
        
        Color3f groundColor = new Color3f(0.3f, 0.4f, 0.2f);
        Material groundMt = new Material();
        groundMt.setSpecularColor(groundColor);
        groundMt.setShininess(0.7f);
        groundMt.setCapability(Material.ALLOW_COMPONENT_READ);
        groundMt.setCapability(Material.ALLOW_COMPONENT_WRITE);
        appearance.setMaterial(groundMt);
    }
    
    public void createSceneGraph() {
        setAppearance();
        
        TransformGroup trans = new TransformGroup();
        
        TransformGroup box = new TransformGroup();
        Transform3D t3d = new Transform3D();
        box.addChild(new Box((float)width / 2, (float)height, (float)depth / 2, appearance));
        t3d.setTranslation(new Vector3d(0.0d, height/2, 0.0d));
        box.setTransform(t3d);
        
        trans.addChild(box);
        
        if (hasRiver) {
            River river = new River(width, depth);
            trans.addChild(river);
            river.createSceneGraph();
        }
        
        addChild(trans);
    }
}