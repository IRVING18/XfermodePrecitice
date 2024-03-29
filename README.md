# DashBoardView 基本表盘

**主要包含知识点：**
- 1.PathDashPathEffect 的使用。
- 2.PathMeasure 的使用
- 3.三角函数的一部分知识

## 一、思路
- 1.画一个弧线，canvas.drawArc()就ok
- 2.画上刻度，画刻度，需要知道弧线总长PathMearsure.getlenth()
- 3.使用paint.setPathEffect()间隙用来画刻度。
- 4.PathDashPathEffect(Path shape, float advance, float phase,Style style)
来添加具体的刻度的形状，间隙等
- 5.drawLine画指针

## 二、画弧线 drawArc()
 <img width="300" height=“100” src="https://github.com/IRVING18/notes/blob/master/android/file/angle.png"></img>
> 注意:代码中ANGLE是代表图中下边小角的角度。

```java
    public static final int   ANGLE  = 120;
    
    ondraw()方法：
    //划弧线
    canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 90 + ANGLE / 2, 360 - ANGLE, false, paint);
```

## 三、画刻度 paint.setPathEffect()
> PathDashPathEffect使用具体看：[PathEffect](https://github.com/IRVING18/notes/blob/master/android/自定义View/2、Paint.md)

- 1、创建添加到PathDashPathEffect中的图形，也就是刻度块
- 2、获取刻度间隔，根据PathMeasure获取弧线长度，然后根据弧线长度/刻度个数，获取刻度间隔。
- 3、创建PathDashPathEffect

```java
   //1、创建添加到PathDashPathEffect中的图形，也就是刻度块
   dashShapePath.addRect(0, -DisplayUtils.dip2px(5), dashWidth, DisplayUtils.dip2px(20), Path.Direction.CW);
   //2、获取刻度间隔，根据PathMeasure获取弧线长度，然后根据弧线长度/刻度个数，获取刻度间隔。
   //获取正常表盘狐线的长度，就把正常弧线的再写一遍，只是为了获取长度。
   Path arcPath = new Path();
   //正常弧线的再写一遍
   arcPath.addArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, 90 + ANGLE / 2, 360 - ANGLE);
   //获取弧线长度
   PathMeasure pathMeasure = new PathMeasure(arcPath, false);
   //获取每个刻度之间的间隔， 减去dashWidth 是因为最后一个刻度绘制的时候的角标是弧线的最后了，所以最后一个刻度超出了弧线边缘。
   float advance = (pathMeasure.getLength() - dashWidth) / 20;
   //3、创建PathDashPathEffect
   //设置PathDashPathEffect 块用于draw中画刻度
   dashPathEffect = new PathDashPathEffect(dashShapePath, advance, 0, PathDashPathEffect.Style.MORPH);
```

## 画指针 canvas.drawLine()
> drawLine(startX，startY，stopX，stopY,paint);   
> 因为drawLine是根据起始坐标和终点坐标画的，所以需要知道终点，就需要三角函数了。

- 1.看图中指针在起始点时，X，Y坐标相对（0，0）点就是   
X = sin(90+ANGLE/2)*Length;   
X = cos(90+ANGLE/2)*Length;   
- 2.而我们在绘制指针时就是原点在(getWidth()/2,getHeight()/2)   
X = sin(90+ANGLE/2)*Length + getWidth()/2;   
X = cos(90+ANGLE/2)*Length + getHeight()/2;   

#### 正余弦三角函数图
![linear](https://github.com/IRVING18/notes/blob/master/android/file/sin.png)
![linear](https://github.com/IRVING18/notes/blob/master/android/file/cos.png)
![linear](https://github.com/IRVING18/notes/blob/master/android/file/sincircle.png)
#### 三角函数示意图
![linear](https://github.com/IRVING18/notes/blob/master/android/file/sincos.png)

