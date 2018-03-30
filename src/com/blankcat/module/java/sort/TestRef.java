package com.blankcat.module.java.sort;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author  zjf
 * @date 创建时间：2018年3月30日 
 * @Description Java四种引用包括强引用，软引用，弱引用，虚引用。
 * https://www.cnblogs.com/yw-ah/p/5830458.html
 */
public class TestRef {

  public static void main(String[] args) {
    //    strongRef();
    //    softRef();
    //    weakRef();
    //    phantomRef();

     
    
    
  }

  /**
   * 
   * @author  zjf
   * @date  2018年3月30日 
   * @Description 虚引用：
   */
  private static void phantomRef() {
    //    垃圾回收时回收，无法通过引用取到对象值，可以通过如下代码实现
        Object obj = new Object();
        PhantomReference<Object> pf = new PhantomReference<Object>(obj, null);
        System.out.println( pf.get());
        System.out.println( pf.isEnqueued());
        obj=null;
        pf.get();//永远返回null
        pf.isEnqueued();//返回是否从内存中已经删除
        System.out.println( pf.get());
        System.out.println( pf.isEnqueued());
    //    虚引用是每次垃圾回收的时候都会被回收，通过虚引用的get方法永远获取到的数据为null，因此也被成为幽灵引用。
    //    虚引用主要用于检测对象是否已经从内存中删除。
  }

  /**
   * 
   * @author  zjf
   * @date  2018年3月30日 
   * @Description 弱引用：
   */
  private static void weakRef() {
    //第二次垃圾回收时回收，可以通过如下代码实现
    Object obj = new Object();
    WeakReference<Object> wf = new WeakReference<Object>(obj);
    System.out.println(wf.get());
    System.out.println(wf.isEnqueued());
    obj = null;
    wf.get();//有时候会返回null
    wf.isEnqueued();//返回是否被垃圾回收器标记为即将回收的垃圾
    System.out.println(wf.get());
    System.out.println(wf.isEnqueued());
//    弱引用是在第二次垃圾回收时回收，短时间内通过弱引用取对应的数据，可以取到，当执行过第二次垃圾回收时，将返回null。
//    弱引用主要用于监控对象是否已经被垃圾回收器标记为即将回收的垃圾，可以通过弱引用的isEnQueued方法返回对象是否被垃圾回收器标记。
  }

  /**
   * 
   * @author  zjf
   * @date  2018年3月30日 
   * @Description 软引用
   */
  private static void softRef() {
    //非必须引用，内存溢出之前进行回收，可以通过以下代码实现
    Object obj = new Object();
    SoftReference<Object> sf = new SoftReference<Object>(obj);
    System.out.println(sf.get());
    obj = null;
    sf.get();//有时候会返回null
    System.out.println(sf.get());
    // 这时候sf是对obj的一个软引用，通过sf.get()方法可以取到这个对象，当然，当这个对象被标记为需要回收的对象时，则返回null；
    //软引用主要用户实现类似缓存的功能，在内存足够的情况下直接通过软引用取值，
    //无需从繁忙的真实来源查询数据，提升速度；当内存不足时，自动删除这部分缓存数据，从真正的来源查询这些数据。
  }

  /**
   * 强引用：
   * @author  zjf
   * @date  2018年3月30日 
   * @Description
   */
  private static void strongRef() {
    // 只要引用存在，垃圾回收器永远不会回收
    Object object =new Object();
   //可直接通过obj取得对应的对象 如obj.equels(new Object());
    System.out.println(object.equals(new Object()));
    //而这样 obj对象对后面new Object的一个强引用，只有当obj这个引用被释放之后，对象才会被释放掉，这也是我们经常所用到的编码形式。
  }
  
  {
    
//对象的强、软、弱和虚引用
//在JDK 1.2以前的版本中，若一个对象不被任何变量引用，那么程序就无法再使用这个对象。也就是说，只有对象处于可触及（reachable）状态，程序才能使用它。从JDK 1.2版本开始，把对象的引用分为4种级别，从而使程序能更加灵活地控制对象的生命周期。这4种级别由高到低依次为：强引用、软引用、弱引用和虚引用。
//
// 
//
//⑴强引用（StrongReference）
//强引用是使用最普遍的引用。如果一个对象具有强引用，那垃圾回收器绝不会回收它。当内存空间不足，Java虚拟机宁愿抛出OutOfMemoryError错误，使程序异常终止，也不会靠随意回收具有强引用的对象来解决内存不足的问题。  ps：强引用其实也就是我们平时A a = new A()这个意思。
//
//⑵软引用（SoftReference）
//如果一个对象只具有软引用，则内存空间足够，垃圾回收器就不会回收它；如果内存空间不足了，就会回收这些对象的内存。只要垃圾回收器没有回收它，该对象就可以被程序使用。软引用可用来实现内存敏感的高速缓存（下文给出示例）。
//软引用可以和一个引用队列（ReferenceQueue）联合使用，如果软引用所引用的对象被垃圾回收器回收，Java虚拟机就会把这个软引用加入到与之关联的引用队列中。
//
//⑶弱引用（WeakReference）
//弱引用与软引用的区别在于：只具有弱引用的对象拥有更短暂的生命周期。在垃圾回收器线程扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存。不过，由于垃圾回收器是一个优先级很低的线程，因此不一定会很快发现那些只具有弱引用的对象。
//弱引用可以和一个引用队列（ReferenceQueue）联合使用，如果弱引用所引用的对象被垃圾回收，Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。
//
//⑷虚引用（PhantomReference）
//“虚引用”顾名思义，就是形同虚设，与其他几种引用都不同，虚引用并不会决定对象的生命周期。如果一个对象仅持有虚引用，那么它就和没有任何引用一样，在任何时候都可能被垃圾回收器回收。
//虚引用主要用来跟踪对象被垃圾回收器回收的活动。虚引用与软引用和弱引用的一个区别在于：虚引用必须和引用队列 （ReferenceQueue）联合使用。当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会在回收对象的内存之前，把这个虚引用加入到与之 关联的引用队列中。
//    
    
  }
}
