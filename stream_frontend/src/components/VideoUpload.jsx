import React, { useState } from 'react';
import upload from '../assets/upload.png';
import { Card, Label, TextInput, Textarea } from 'flowbite-react';
import axios from 'axios'

export default function VideoUpload() {
    const [data, setData] = useState({
        title: "",
        description: "",
        file: null,
    });
    const [progress, setProgress] = useState(0);
    const [uploading, setUploading] = useState(false);
    const [message, setMessage] = useState("");

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleFileChange = (e) => {
        setData((prevData) => ({
            ...prevData,
            file: e.target.files[0], // Store the file object
        }));
    };

    const uploadFileHandler = (e) => {
        if (!data.file) {
            setMessage("Please select a file to upload.");
            return;
        }
        uploadToServer(data)
        e.preventDefault()
        console.log(data);
        setUploading(true);
        setProgress(0);
        setMessage("");
    };
    const uploadToServer=async (data)=>{
      try {
        const response = await axios.post('http://localhost:8080/api/v1/videos', null,{
          params:data
        });
        console.log('Response:', response.data);
      } catch (error) {
        console.error('Error:', error);
      }
    }
    return (
        <div className="py-6">
            <Card>
                <h1 className="text-gray-400">Upload Videos</h1>
                <form className="space-y-5 gap-2">
                    <div>
                        <div className="mb-2 block">
                            <Label htmlFor="title" value="Enter Title" />
                        </div>
                        <TextInput
                            id="title"
                            name="title"
                            value={data.title}
                            onChange={handleInputChange}
                            placeholder="Enter title"
                        />
                    </div>

                    <div>
                        <div className="max-w-md">
                            <div className="mb-2 block">
                                <Label htmlFor="description" value="Video description" />
                            </div>
                            <Textarea
                                id="description"
                                name="description"
                                value={data.description}
                                onChange={handleInputChange}
                                placeholder="Video description..."
                                required
                                rows={4}
                            />
                        </div>
                    </div>

                    <div className="flex items-center justify-center space-x-6">
                        <div className="shrink-0">
                            <img className="h-16 w-16 object-cover rounded-full" src={upload} alt="Upload icon" />
                        </div>
                        <label className="block">
                            <span className="sr-only">Choose video file</span>
                            <input
                                type="file"
                                onChange={handleFileChange} // Handle file input change
                                className="block w-full text-sm text-slate-500
                                          file:mr-4 file:py-2 file:px-4
                                          file:rounded-full file:border-0
                                          file:text-sm file:font-semibold
                                          file:bg-violet-50 file:text-violet-700
                                          hover:file:bg-violet-100"
                            />
                        </label>
                    </div>
                </form>

                <div className="flex justify-center">
                    <button
                        onClick={(e)=>{uploadFileHandler(e)}}
                        className="mt-4 py-2 px-4 text-white bg-blue-600 rounded-full 
                                hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500"
                        disabled={uploading}
                    >
                        {uploading ? 'Uploading...' : 'Upload'}
                    </button>
                </div>

                {uploading && (
                    <div className="mt-4">
                        <progress value={progress} max="100" className="w-full"></progress>
                        <p className="text-center mt-2">{progress}%</p>
                    </div>
                )}

                {message && (
                    <div className="mt-4 text-center">
                        <p className="text-lg text-blue-500">{message}</p>
                    </div>
                )}
            </Card>
        </div>
    );
}
