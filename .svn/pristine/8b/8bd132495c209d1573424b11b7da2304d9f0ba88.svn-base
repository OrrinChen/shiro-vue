#文件路径
filepath=shiro_jwt
#文件名
filename=shiro_jwt-0.0.1-SNAPSHOT.jar
#文件名2
filename2=shiro_jwt-0.0.1-SNAPSHOT-9292.jar

#查询后台进程，存在则杀死
PID=$(ps -ef | grep $filename2 | grep -v grep | awk '{ print $2 }')
if [ ${PID} ]; then
  echo 'Application is stpping...'
  echo kill $PID DONE
  kill -9 $PID
else
  echo 'Application is already stopped...'
fi

# 查找当前目录下是否存在backup文件夹
cd /root/shiro-vue/$filepath/
if [ ! -d "backup/" ]; then
  # 如果不存在在根目录下的output下新建canbus文件夹
  mkdir backup
else
  echo "backup文件夹已经存在,不需要创建"
fi

#备份文件
var=$(date +%Y年%m月%d日%H:%M:%S)
mv $filename2 backup/$var-$filename2
echo "backup jar success!"

#复制文件，启动程序
cp /var/lib/jenkins/workspace/shiro-vue/$filepath/target/$filename /root/shiro-vue/$filepath/$filename2
cd /root/shiro-vue/$filepath/
#指定内存大小
#BUILD_ID=dontKillMe nohup java -Xms512m -Xmx512m -jar $filename2 &
BUILD_ID=dontKillMe nohup java -jar $filename2 &

#查询后台进程，判断是否启动成功
PID=$(ps -ef | grep $filename2 | grep -v grep | awk '{ print $2 }')
if [ ${PID} ]; then
  echo 'Application is started...'
  echo pid: $PID
else
  echo 'Application is already stopped...'
fi

#和Jenkins的配置Send files or execute commands over SSH勾选的Exec in pty相结合，脚本执行状态正确返回
sleep 1
