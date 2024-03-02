import Vue from "vue";

import loading from "@/components/Loading.vue";

let LoadingConstructor = Vue.extend(loading);

let instance;

let close_status = false;

let timer = null;

//设置自动关闭时长
const TIME = 6*1000;

const Loading = (title) => {
    close_status = false;

    if (instance) {
        removeChild();
    }
    instance = new LoadingConstructor();
    instance.$data.msg.title = title
    instance.$mount();

    document.body.appendChild(instance.$el);

    autoClose();

    return instance;
};

Loading.close = function() {
    close_status = true;
    removeChild();
};

function removeChild() {
    if (instance) {
        instance.$el.parentNode.removeChild(instance.$el);
        instance = null;
    }
}

//未关闭则自动关闭
function autoClose() {
    clearTimeout(timer);

    timer = setTimeout(() => {
        if (!close_status) {
            Loading.close();
        }
    }, TIME);
}

export default Loading;
