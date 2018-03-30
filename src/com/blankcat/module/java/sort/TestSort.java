package com.blankcat.module.java.sort;
/**
 * @author  zjf
 * @date 创建时间：2018年3月30日 
 * @Description 二分查找
 * https://www.zhihu.com/question/36132386
 */
public class TestSort {
  public static void main(String[] args) {
    int [] nums={1,2,5,3,6,9};
    System.out.println(sort(nums,5));
    System.out.println(sort(nums,15));
    
    
  }
  
  private static int sort(int [] nums,int  target ){
//    Arrays.sort(nums);
    int low =0;
    int hight=nums.length-1;
    for(int i=0;i<nums.length;i++){
      int mid=low+(hight-low)/2;
      if(target>nums[mid]){
        low=mid;
      }
      if(target<nums[mid]){
        hight=mid;
      }
      if(target==nums[mid]){
        return mid;
      }
    }
    return -1;
  }
}
