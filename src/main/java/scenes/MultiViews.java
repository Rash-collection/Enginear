/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package scenes;

import engine.View;
import funks.Disposer;
import funks.Initializer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.HashMap;
import javax.swing.JPanel;
import means.Launcher;

/**
 * 
 * @author rash4
 * @param <M>       Scene type
 */
public class MultiViews <M extends MultiViews <M>> extends engine.Scene<M> 
        implements Viewer, Initializer<M>, Disposer<M>{
    public MultiViews(String name){
        super(name);
        this.SUB_SCENE = new HashMap<>();
        // for conveninece
        this.SUB_SCENE.put("Main", new ViewableScene("Main", new Rectangle(Launcher.size())));
    }
    public MultiViews(String name, Rectangle bounds){
        super(name);
        this.SUB_SCENE = new HashMap<>();
        // for conveninece
        final var bnd = (bounds == null)? new Rectangle(Launcher.size()) : bounds;
        this.SUB_SCENE.put("Main", new ViewableScene("Main", bnd));
    }
    public M addScene(String name, ViewableScene<?> view){
        if(view != null && name != null)
            this.SUB_SCENE.putIfAbsent(name, view);
        return this.self();
    }
    @Override public M setInitializer(Initializer<M> init){
        this.initialize = init;
        return this.self();
    }
    @Override public M setDisposer(Disposer dispose){
        this.dispose = dispose;
        return this.self();
    }
    public void autoResizer(){
        for(var scene : this.SUB_SCENE.values()){
            if(!scene.isActive())continue;
            scene.getResizer().resize();
        }
    }
    /**This is like default render method, also overridable for superclasses below this.*/
    public void autoRender(Graphics2D grr){
        for(var scene : this.SUB_SCENE.values()){
            if(!scene.isActive())continue;
            if(scene instanceof ViewableScene vew && vew.getRender() instanceof funks.Viewable vb){
                final var zoom = vew.getView().zoomer();
                vb.render(grr, zoom, vew.getView().getDelta(zoom));
            } else scene.getRender().render(grr);
        }
    }
    /**This is like default updater method, also overridable for superclasses below this.*/
    public void autoUpdater(){
        for(var scene : this.SUB_SCENE.values()){
            if(!scene.isActive())continue;
            scene.getUpdates().update();
        }
    }
    public boolean autoOnLoad(JPanel owner){
        boolean loads = true;
        for(var scene : this.SUB_SCENE.values()){
            loads = loads && scene.onLoad(owner, false);
        }return loads;
    }
    public boolean autoOnLeave(JPanel owner){
        boolean leaves = true;
        for(var scene : this.SUB_SCENE.values()){
            leaves = leaves && scene.onLeave(owner);
        }return leaves;
    }
    public ViewableScene<?> getSubScene(String sceneName){return this.SUB_SCENE.get(sceneName);}
    public ViewableScene<?> getMain(){return this.SUB_SCENE.get("Main").self();}
    protected Collection<ViewableScene<?>> getSubs(){return this.SUB_SCENE.values();}
    @Override public View getView(){return this.getMain().getView();}
    @Override public M initialize(){return this.initialize.initialize();}
    @Override public void dispose(){this.dispose.dispose();}
    
    /**the <?> for different viewable scenes and never MultiScenes object, hence the separation.*/
    private final HashMap<String ,ViewableScene<?>> SUB_SCENE;
    private Initializer<M> initialize;
    private Disposer dispose;
}