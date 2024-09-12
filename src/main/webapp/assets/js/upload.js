document.addEventListener('DOMContentLoaded', function() {
    const uploadForm = document.getElementById('uploadForm');
    const fileInput = document.getElementById('fileInput');
    const dropArea = document.getElementById('dropArea');
    const progressBar = document.getElementById('progressBar');
    const uploadMessage = document.getElementById('uploadMessage');

    // Prevent default behavior (Prevent file from being opened)
    ['dragenter', 'dragover', 'dragleave', 'drop'].forEach(eventName => {
        dropArea.addEventListener(eventName, preventDefaults, false);
    });

    // Highlight drop area when file is dragged over it
    ['dragenter', 'dragover'].forEach(eventName => {
        dropArea.addEventListener(eventName, () => dropArea.classList.add('highlight'), false);
    });

    ['dragleave', 'drop'].forEach(eventName => {
        dropArea.addEventListener(eventName, () => dropArea.classList.remove('highlight'), false);
    });

    // Handle dropped files
    dropArea.addEventListener('drop', handleDrop, false);

    // Handle file selection via input
    fileInput.addEventListener('change', handleFileSelect, false);

    // Handle form submission
    uploadForm.onsubmit = function(event) {
        event.preventDefault();
        uploadFile(new FormData(uploadForm));
    };

    function preventDefaults(e) {
        e.preventDefault();
        e.stopPropagation();
    }

    function handleDrop(e) {
        const files = e.dataTransfer.files;
        const formData = new FormData();
        formData.append('file', files[0]); // Assuming single file upload
        uploadFile(formData);
    }

    function handleFileSelect(e) {
        const files = fileInput.files;
        const formData = new FormData();
        formData.append('file', files[0]); // Assuming single file upload
        uploadFile(formData);
    }

    function uploadFile(formData) {
        const xhr = new XMLHttpRequest();
        xhr.open('POST', 'UploadServlet', true);

        xhr.upload.onprogress = function(event) {
            if (event.lengthComputable) {
                const percentComplete = Math.round((event.loaded / event.total) * 100);
                progressBar.value = percentComplete;
                progressBar.innerText = percentComplete + '%';
            }
        };

        xhr.onload = function() {
            if (xhr.status === 200) {
                showUploadMessage('File uploaded successfully!', 'success');
                window.location.href = 'success.jsp'; // Redirect on success
            } else {
                showUploadMessage('File upload failed: ' + xhr.responseText, 'error');
            }
        };

        xhr.onerror = function() {
            showUploadMessage('There was an error with the upload.', 'error');
        };

        xhr.send(formData);
    }

    function showUploadMessage(message, type) {
        uploadMessage.textContent = message;
        uploadMessage.className = type;
    }
});
