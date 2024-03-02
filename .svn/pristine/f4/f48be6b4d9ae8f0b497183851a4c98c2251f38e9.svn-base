#文件路径
filepath=dist
#文件名
filename=dist
#文件名2
filename2=nginx

#查询后台进程，存在则杀死
systemctl stop nginx

# 查找当前目录下是否存在backup文件夹
cd /usr/share/nginx/shiro-vue/vue/
if [ ! -d "dist-backup/" ]; then
  # 如果不存在在根目录下的output下新建canbus文件夹
  mkdir dist-backup
else
  echo "dist-backup文件夹已经存在,不需要创建"
fi

#备份文件
var=$(date +%Y年%m月%d日%H:%M:%S)
cd dist-backup
mkdir $var-$filename
cd ..
mv $filename/ dist-backup/$var-$filename/
echo "backup dist success!"

#复制文件，启动程序
mkdir dist
cp -r /var/lib/jenkins/workspace/shiro-vue/vue/dist/ /usr/share/nginx/shiro-vue/vue/
systemctl start nginx

#查询后台进程，判断是否启动成功


#和Jenkins的配置Send files or execute commands over SSH勾选的Exec in pty相结合，脚本执行状态正确返回
sleep 1
